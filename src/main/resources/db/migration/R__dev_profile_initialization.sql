create table MENU_ITEM (
  id bigint not null,
  parent bigint not null,
  us_View_Bean_Name varchar(255),
  us_Controller_Bean_Name varchar(255),
  bar_Name varchar(255),
  expanded boolean,
  PRIMARY KEY (id)
);

INSERT INTO MENU_ITEM(id, parent, us_View_Bean_Name, us_Controller_Bean_Name, bar_Name, expanded)
VALUES(0, -1 ,'MainView', 'MainController', 'System', 1);

INSERT INTO MENU_ITEM(id, parent, us_View_Bean_Name, us_Controller_Bean_Name, bar_Name, expanded)
VALUES(1, 0 ,'CompanyView', 'CompanyController', 'Company', 0);

INSERT INTO MENU_ITEM(id, parent, us_View_Bean_Name, us_Controller_Bean_Name, bar_Name, expanded)
VALUES(2, 0 ,'ReportView', 'ReportController', 'Report', 0);