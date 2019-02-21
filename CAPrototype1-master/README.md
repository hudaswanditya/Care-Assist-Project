# Care Assist 2.0 - The mobile app
HTW Praxis-Projekt SoSe 17 – ***Mobile Computing in der ambulanten Pflege***

A Student project from [HTW Berlin](https://www.htw-berlin.de) in cooperation with David Koschnick, which is the second prototype of an mobile application for the ambulant care. This Repository includes the mobile Android app.

- [Info](#info)
- [Dependencies](#dependencies)
- [Usage](#usage)
  * [Import the project](#import-the-project)
  * [Android Manifest // Classes to mention](#android-manifest----classes-to-mention)

## Info
http://imi-bachelor.htw-berlin.de/studium/projekte/showtime-im-sommersemester-2017/#c32948

## Dependencies
All dependencies are handle by **Gradle**
- **Graphview** library for the visualization of vital values
- **Couchbase Lite** the local NoSQL database
- **Gson** the interface between POJO and JSON

## Usage
### Import the project
- Download AndroidStudio [here](https://developer.android.com/studio/index.html).
- Check out project from Version Control (Git)
- paste your personal Git Repository URL (for example: https://yourname@git.myhki.de/careassist/SoSe17/CAPrototype1.git)
- Choose a location on your local hard drive
- Clone

### Android Manifest // Classes to mention
- Launching activity is `de.careassist.app.Login`
- Database related tasks in `de.careassist.app.DataManager`
- Dummy Data is generated in `de.careassist.app.dummy.DummyCarer`, `de.careassist.app.dummy.DummyClient` and subclasses

### Overview of classes and functions
***java root directory: app/java/de/careassist/app/java***

|                **(visible) activity**                 |    **directory**     |        **java class**         | **type** |                                        **description**                                         |          **related XML**          |
|:-----------------------------------------------------:|:--------------------:|:-----------------------------:|:--------:|:----------------------------------------------------------------------------------------------:|:---------------------------------:|
|                         Login                         |         root         |         LogInActivity         | activity |                   manages the login process, sets up the database connection                   |        activity_log_in.xml        |
|                         Login                         |         root         |            Splash             | activity |                  sets up the welcome screen if user entered valid credentials                  |        welcome_screen.xml         |
|                    Routenübersicht                    |        Route         |         RouteActivity         | activity |                              sets up the route activity with menu                              |        activity_route.xml         |
|                    Routenübersicht                    |        Route         |      ClientListFragment       | fragment |                  sets up list of clients, handles interaction with list items                  | route_element.xml route_list.xml  |
|                    Routenübersicht                    |        Route         |     ClientListViewAdapter     | adapter  |                sets up list items and handles interaction with icons, e.g. maps                |         route_element.xml         |
|                       Aufgaben                        |         Todo         |      ClientViewFragment       | fragment |              manages fragments and exchanges them if a menu option gets selected               |      fragment_todo_view.xml       |
|                       Aufgaben                        |         Todo         |        DialogShiftTask        |  dialog  |                             sets up the dialog for shifting a task                             |       dialog_shift_task.xml       |
|                       Aufgaben                        |         Todo         |      FixedNotesFragment       | fragment |                                  sets up fixed notes fragment                                  |   fragment_fixed_notes_list.xml   |
|                       Aufgaben                        |         Todo         |          InfoDialog           |  dialog  |                       sets up the dialog displaying the todo information                       |          dialog_info.xml          |
|                       Aufgaben                        |         Todo         |   MyNoteRecyclerViewAdapter   | adapter  |                                       sets up list items                                       |         fragment_note.xml         |
|                       Aufgaben                        |         Todo         |  NotesFromYesterdayFragment   | fragment |                                    sets up logbook fragment                                    | fragment_notes_from_yesterday.xml |
|                       Aufgaben                        |         Todo         | TodoExpandableListViewAdapter | adapter  |                   sets up todo items and handles interaction, e.g. checkbox                    |         fragment_todo.xml         |
|                       Aufgaben                        |         Todo         |         TodoFragment          | fragment |                         sets up todo fragment and handles interaction                          | fragment_todo_list_expandable.xml |
|                       Aufgaben                        |         Todo         |       ToDoNotesFragment       | fragment |                      sets up note fragments and handles activity updates                       |     fragment_to_do_notes.xml      |
|                      Stammdaten                       |    BasicDataBase     |     BasicDataBaseFragment     | fragment |                                sets up basic data base elements                                |   fragment_basic_data_base.xml    |
|                      Vitalwerte                       |        Vital         |     BloodPressureFragment     | fragment |                                                                                                |         graph_layout.xml          |
|                      Vitalwerte                       |        Vital         |      BloodSugarFragment       | fragment |                                                                                                |         graph_layout.xml          |
|                      Vitalwerte                       |        Vital         |      DialogAddVitalValue      |  dialog  |                           sets up the dialog and handles user input                            |    dialgog_add_vital_value.xml    |
|                      Vitalwerte                       |        Vital         |      TemperatureFragment      | fragment |                                                                                                |         graph_layout.xml          |
|                      Vitalwerte                       |        Vital         |         VitalFragment         | fragment |                         sets up vital fragment and handles interaction                         |        fragment_vital.xml         |
|                      Vitalwerte                       |        Vital         |          VitalValues          | datatype |               datatype for vital values (bloodpressure, bloodsugar, temperature)               |                                   |
|                      Medikation                       |      Medication      |   MedicineHeadlineFragment    | fragment |                             sets up medicine headline as fragment                              |      fragment_medication.xml      |
|                      Medikation                       |      Medication      |     MedicineListFragment      | fragment |                               sets up (hard coded) medicine list                               |  fragment_medicinelist_list.xml   |
|                      Medikation                       |      Medication      |   MedicineOverviewFragment    | fragment |                                  sets up medication fragment                                   |  fragment_medicine_overview.xml   |
|                        Verlauf                        |       LogBook        |        LogBookFragment        | fragment |             set up logbook fragment and handle interaction, e.g. sorting elements              |       fragment_logbook.xml        |
|                        Verlauf                        |       LogBook        |    LogBookHeadlineFragment    | fragment |                              sets up logbook headline as fragment                              |     fragment_logbook_item.xml     |
|                        Verlauf                        |       LogBook        | MyLogBookRecyclerViewAdapter  | adapter  |                               populates logbook items with data                                |     fragment_logbook_item.xml     |
|                        Profil                         |       Profile        |     DialogChangePassword      |  dialog  |                           sets up the dialog and handles user input                            |    dialog_change_password.xml     |
|                        Profil                         |       Profile        |        ProfileActivity        | activity |                        sets up profile elements and handles interaction                        |       activity_profile.xml        |
| Aufgaben, Stammdaten, Vitalwerte, Medikation, Verlauf |         root         |      ClientViewActivity       | activity | sets up the fragments that get exchanged with selecting a different menu item, manages changes |     activity_client_view.xml      |
|                       Datenbank                       |         root         |          DataManager          | activity |                                sets up the database connection                                 |                                   |
|                    Gson Exception                     |         Gson         |                               |          |                   to convert special data types which are not covert by gson                   |                                   |
|                Floating Aktion Button                 | FloatingActionButton |         DialogAddNote         |  Dialog  |                            a dialog to add a note or a pinned note                             |                                   |

***all style related files (xmly, icons, images) are located in app/java/de/careassist/app/res***
