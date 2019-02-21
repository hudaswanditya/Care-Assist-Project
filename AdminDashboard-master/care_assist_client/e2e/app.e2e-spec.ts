import { CareassistAdminPage } from './app.po';

describe('careassist-admin App', () => {
  let page: CareassistAdminPage;

  beforeEach(() => {
    page = new CareassistAdminPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
