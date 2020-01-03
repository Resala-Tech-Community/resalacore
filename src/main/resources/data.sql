INSERT INTO `VolunteerTypeEntity` (id,name) VALUES (1,'normal');
INSERT INTO `VolunteerTypeEntity` (id,name) VALUES (2,'supervisor');
INSERT INTO `UniversityEntitiy` (id,name) VALUES (1,'Nile');
INSERT INTO `RegionEntity` (id,name) VALUES (1,'Maadi');
INSERT INTO `NetworkTypeEntity` (id,name) VALUES (1,'vodafone');
INSERT INTO `CollageEntity` (id,name,university_id) VALUES (1,'business',1);
INSERT INTO `UniversitySpecializationEntity` (id,name,collage_id) VALUES (1,'Accountent',1);
INSERT INTO `VolunteerEntity` (id,birthDate,gender,identificationNumber,joinDate,miniCamp,name,notes,phoneNumber,tshirt,UniversitySpecialization_id,networkType_id,volunteerType_id) VALUES (1,'20/12/1990',1,'12345678901234','01/01/2020',1,'Yahia Saleh','Test Notes','01006537372',1,1,1,1);