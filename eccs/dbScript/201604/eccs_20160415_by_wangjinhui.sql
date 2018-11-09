-----修改了表中的字段，移除了原有的user_id,rtime,增加了转正记录人，转正时间，离职记录人，离职时间
ALTER TABLE t_hr_entry_record ADD fuser_id int(11) DEFAULT NULL;
ALTER TABLE t_hr_entry_record ADD quser_id int(11) DEFAULT NULL;
ALTER TABLE t_hr_entry_record ADD frtime date DEFAULT NULL;
ALTER TABLE t_hr_entry_record ADD qrtime date DEFAULT NULL;
ALTER TABLE t_hr_entry_record DROP user_id;
ALTER TABLE t_hr_entry_record DROP rtime;