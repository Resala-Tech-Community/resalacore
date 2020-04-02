
INSERT INTO `EventEntity` (id,eventDate,name,startTime,lastTimeToReg) VALUES (1,'1582310942000','test event 1','09:00','10:00');
INSERT INTO `EventEntity` (id,eventDate,name,startTime,lastTimeToReg) VALUES (2,'1582397342000','test event 2','09:00','10:00');
INSERT INTO `EventEntity` (id,eventDate,name,startTime,lastTimeToReg) VALUES (3,'1582138142000','test event 3','09:00','10:00');
INSERT INTO `EventEntity` (id,eventDate,name,startTime,lastTimeToReg) VALUES (4,'1582051742000','test event 4','09:00','10:00');
INSERT INTO `EventEntity` (id,eventDate,name,startTime,lastTimeToReg) VALUES (5,'1581965342000','test event 5','09:00','10:00');
INSERT INTO `EventEntity` (id,eventDate,name,startTime,lastTimeToReg) VALUES (6,'1581878942000','test event 6','09:00','10:00');
INSERT INTO `BranchEntity`(id,name) VALUES(1,'TEST Branch');
INSERT INTO `BranchEventEntity`(branchEntity_id,eventEntity_id) VALUES(1,1);
INSERT INTO `BranchEventEntity`(branchEntity_id,eventEntity_id) VALUES(1,2);
INSERT INTO `BranchEventEntity`(branchEntity_id,eventEntity_id) VALUES(1,3);
INSERT INTO `BranchEventEntity`(branchEntity_id,eventEntity_id) VALUES(1,4);
INSERT INTO `BranchEventEntity`(branchEntity_id,eventEntity_id) VALUES(1,5);
INSERT INTO `BranchEventEntity`(branchEntity_id,eventEntity_id) VALUES(1,6);

INSERT INTO `VolunteerTypeEntity` (id,name) VALUES (1,'normal');
INSERT INTO `VolunteerTypeEntity` (id,name) VALUES (2,'supervisor');
INSERT INTO `UniversityEntitiy` (id,name) VALUES (1,'Nile');
INSERT INTO `RegionEntity` (id,name) VALUES (1,'Maadi');
INSERT INTO `NetworkTypeEntity` (id,name) VALUES (1,'vodafone');
INSERT INTO `CollageEntity` (id,name,university_id) VALUES (1,'business',1);
INSERT INTO `UniversitySpecializationEntity` (id,name,collage_id) VALUES (1,'Accountent',1);
INSERT INTO `VolunteerEntity` (id,birthDate,gender,identificationNumber,joinDate,miniCamp,name,notes,phoneNumber,tshirt,UniversitySpecialization_id,networkType_id,volunteerType_id) VALUES (10,'20/12/1990',1,'12345678901234','01/01/2020',1,'Yahia Saleh','Test Notes','01006537372',1,1,1,1);
INSERT INTO `VolunteerEntity` (id,birthDate,gender,identificationNumber,joinDate,miniCamp,name,notes,phoneNumber,tshirt,UniversitySpecialization_id,networkType_id,volunteerType_id) VALUES (11,'20/12/1990',1,'12345678901234','01/01/2020',1,'Yahia Saleh','Test Notes','010',1,1,1,1);