var Couchbase = require("couchbase");
var Express = require("express");
var BodyParser = require("body-parser");
var Request = require("request");
const cors = require('cors');
var app = Express();
var N1qlQuery = Couchbase.N1qlQuery;

app.use(BodyParser.json());
app.use(BodyParser.urlencoded({ extended: true }));
app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
});
app.use(cors());

var bucket = (new Couchbase.Cluster("94.177.230.91:8091")).openBucket("care_assist");
process.env.NODE_TLS_REJECT_UNAUTHORIZED = "0";
var makePostRequest = function(url, body, callback) {
    Request.put(url, { json: body },
        function (error, response, body) {
            if (!error && response.statusCode == 200) {
                callback(null, body);
            } else {
                callback(error, null);
            }
        }
    );
}

app.get("/client", function(req, res) {
    bucket.query(N1qlQuery.fromString("SELECT _sync.rev as token, properties FROM `care_assist` WHERE type ='client' and _sync.rev is not missing"), function(error, result) {
        if(error) {
            return res.status(400).send(error);
        }
        res.send(result);
    });
});

app.get("/carer", function(req, res) {
    bucket.query(N1qlQuery.fromString("SELECT _sync.rev as token, properties, type FROM `care_assist` WHERE type ='carer' and _sync.rev is not missing"), function(error, result) {
        if(error) {
            return res.status(400).send(error);
        }
        res.send(result);
    });
});

app.get("/client/:id", function(req, res) {
    bucket.query(N1qlQuery.fromString("SELECT _sync.rev as token, properties FROM `care_assist` WHERE type ='client' AND _sync.rev is not missing AND properties.id = " + req.params.id), function(error, result) {
        if(error) {
            return res.status(400).send(error);
        }
        res.send(result);
    });
});

app.get("/carer/:id", function(req, res) {
    bucket.query(N1qlQuery.fromString("SELECT _sync.rev as token, properties FROM `care_assist` WHERE type ='carer' AND _sync.rev is not missing AND properties.id = " + req.params.id), function(error, result) {
        if(error) {
            return res.status(400).send(error);
        }
        res.send(result);
    });
});

app.put("/create", cors(), function(req, res, callback) {
    // if(!req.body.id) {
    //     return res.status(400).send({"status": "error", "message": "An ID is required"});
    // }
    var body = {type: req.body.type, properties: req.body.properties};
    Request.put("https://0.0.0.0:4985/care_assist/"+ req.body.type +"_"+ req.body.id +"?new_edits=true&rev=" + req.body._rev, { json: body },
        function (error, response, body) {
            if (!error && response.statusCode == 200) {
                callback(null, body);
            } else {
                callback(error, null);
            }
        }
    );
});

app.delete("/delete", function(req, res, callback) {
    Request.delete("https://0.0.0.0:4985/care_assist/carer_" + req.body.id,
        function (error, response) {
            if (!error && response.statusCode == 200) {
                callback(null,req.body.id);
            } else {
                callback(error, null);
            }
        }
    );
});

var server = app.listen(3000, function() {
    console.log("Listening on port %s...", server.address().port);
});
