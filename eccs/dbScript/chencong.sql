UPDATE dept SET NAME='造价一部',sname='造价一部' WHERE NAME='造价一组'; 
UPDATE dept SET NAME='造价二部',sname='造价二部' WHERE NAME='造价二组'; 
UPDATE dept SET NAME='造价三部',sname='造价三部' WHERE NAME='造价三组'; 
UPDATE dept SET NAME='造价安装部',sname='造价安装部' WHERE NAME='造价安装组';
UPDATE proce_step_def SET step_name='整理编审依据' WHERE UUID=11; 
UPDATE proce_step_def SET step_name='登记资料交接' WHERE UUID=9;  
ALTER TABLE project_contact ADD im_no VARCHAR(30) NULL; 
INSERT INTO annex_type (NAME,forcombo,step_name,ctime,STATUS) VALUES ('交接单','yes','2',NOW(),1); 
INSERT INTO annex_type (NAME,forcombo,step_name,ctime,STATUS) VALUES ('确认签收单','yes','10',NOW(),1);
INSERT INTO annex_type (NAME,forcombo,step_name,ctime,STATUS) VALUES ('经济分析表','yes','14',NOW(),1);
INSERT INTO annex_type (NAME,forcombo,step_name,ctime,STATUS) VALUES ('回访单','yes','13',NOW(),1); 
 
INSERT INTO res (id,pid,NAME,url,STATUS) VALUES ('003002007','003002','交接单删除','/step2/deletesz.htm',2); 
INSERT INTO res (id,pid,NAME,url,STATUS) VALUES ('003014007','003010','经济分析表删除','/step14/deletesz.htm',2); 
INSERT INTO res (id,pid,NAME,url,STATUS) VALUES ('003010012','003010','确认签收单删除','/step10/deletesz.htm',2); 
INSERT INTO res (id,pid,NAME,url,STATUS) VALUES ('003015009','003015','提交审定','/step15/toValidate.htm',2);

ALTER TABLE project MODIFY handle_manager_id VARCHAR(50) NULL; 

INSERT INTO contract (id,NO) VALUES (100,'2017ZJ0001');
INSERT INTO project (id,NO) VALUES (131,'20170001'); 

ALTER TABLE step15 ADD confirm_id INT(1)  DEFAULT 0;  
ALTER TABLE step15 ADD deal_user_id INT(20) NULL; 
ALTER TABLE step15 ADD receive_manager_id INT(11) NULL; 
ALTER TABLE step15 ADD validate_status INT(1)  DEFAULT 0;
ALTER TABLE bid_plan MODIFY bid_dept_id VARCHAR(64)
 

  