SET DEFINE OFF;
Insert into LLSFW_V2.TS_PORTAL
   (FUNCTION_CODE, PORTAL_CODE, PORTAL_TITLE, PORTAL_HEIGHT, PORTAL_URL, 
    CREATE_BY, CREATE_DATE)
 Values
   ('portalController', '27117932c05c4481a24e25deca1a6e52', '请求次数统计', 500, 'portalController/reportRequestCount', 
    'admin', TO_DATE('02/23/2016 13:15:29', 'MM/DD/YYYY HH24:MI:SS'));
Insert into LLSFW_V2.TS_PORTAL
   (FUNCTION_CODE, PORTAL_CODE, PORTAL_TITLE, PORTAL_HEIGHT, PORTAL_URL, 
    CREATE_BY, CREATE_DATE)
 Values
   ('portalController', 'b6c7f3c6e376475ba88985944e87b737', '异常率统计', 500, 'portalController/reportExceptionCount', 
    'admin', TO_DATE('02/23/2016 15:02:18', 'MM/DD/YYYY HH24:MI:SS'));
Insert into LLSFW_V2.TS_PORTAL
   (FUNCTION_CODE, PORTAL_CODE, PORTAL_TITLE, PORTAL_HEIGHT, PORTAL_URL, 
    CREATE_BY, CREATE_DATE)
 Values
   ('portalController', 'f61c9fee71bb41e89f21d2c58948e8be', '介绍', 200, 'portalController/remarkInit', 
    'admin', TO_DATE('02/23/2016 13:38:04', 'MM/DD/YYYY HH24:MI:SS'));
Insert into LLSFW_V2.TS_PORTAL
   (FUNCTION_CODE, PORTAL_CODE, PORTAL_TITLE, PORTAL_HEIGHT, PORTAL_URL, 
    CREATE_BY, CREATE_DATE)
 Values
   ('portalController', '7e0c67df0d0f428fb68e52eff93b0463', 'session数量统计', 500, 'portalController/reportSessionCount', 
    'admin', TO_DATE('02/23/2016 14:11:39', 'MM/DD/YYYY HH24:MI:SS'));
Insert into LLSFW_V2.TS_PORTAL
   (FUNCTION_CODE, PORTAL_CODE, PORTAL_TITLE, PORTAL_HEIGHT, PORTAL_URL, 
    CREATE_BY, CREATE_DATE)
 Values
   ('portalController', '11129bc20e8c40d6a7e64c289eb864a6', '在线用户', 200, 'portalController/onlineSecctionInit', 
    'admin', TO_DATE('02/23/2016 12:21:33', 'MM/DD/YYYY HH24:MI:SS'));
COMMIT;
