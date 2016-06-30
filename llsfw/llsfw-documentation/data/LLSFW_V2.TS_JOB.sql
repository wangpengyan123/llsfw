SET DEFINE OFF;
Insert into LLSFW_V2.TS_JOB
   (JOB_CODE, JOB_NAME, ORG_CODE, CREATE_BY, CREATE_DATE)
 Values
   ('hr', '人事部门', 'activiti_test', 'admin', TO_DATE('02/02/2016 11:02:09', 'MM/DD/YYYY HH24:MI:SS'));
Insert into LLSFW_V2.TS_JOB
   (JOB_CODE, JOB_NAME, ORG_CODE, CREATE_BY, CREATE_DATE)
 Values
   ('deptLeader', '部门领导', 'activiti_test', 'admin', TO_DATE('02/01/2016 14:30:45', 'MM/DD/YYYY HH24:MI:SS'));
Insert into LLSFW_V2.TS_JOB
   (JOB_CODE, JOB_NAME, ORG_CODE, CREATE_BY, CREATE_DATE, 
    UPDATE_BY, UPDATE_DATE)
 Values
   ('TEST_JOB', '测试岗位', 'ORG_ADMIN', '-1', TO_DATE('04/25/2014 11:41:06', 'MM/DD/YYYY HH24:MI:SS'), 
    'admin', TO_DATE('05/03/2014 13:54:12', 'MM/DD/YYYY HH24:MI:SS'));
Insert into LLSFW_V2.TS_JOB
   (JOB_CODE, JOB_NAME, ORG_CODE, CREATE_BY, CREATE_DATE, 
    UPDATE_BY, UPDATE_DATE)
 Values
   ('readonly', '只读岗位', 'ORG_ADMIN', 'admin', TO_DATE('05/03/2014 13:51:26', 'MM/DD/YYYY HH24:MI:SS'), 
    'admin', TO_DATE('05/03/2014 13:54:20', 'MM/DD/YYYY HH24:MI:SS'));
COMMIT;
