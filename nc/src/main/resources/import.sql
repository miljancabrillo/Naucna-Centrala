INSERT INTO user_details (id, city, country, email, name, password, surname, title, username, is_assigned_as_editor) VALUES (1,'Novi Sad','Serbia','miljancabrilo@yahoo.com','Miljan Cabrilo','$2a$10$OGNKIcwqjxcR7xfrztTix.PZ9nDCpfLBze.FJzihRxUs8me4DoWVK','Cabrilo','Bsc','miljan', true);
INSERT INTO user_details (id, city, country, email, name, password, surname, title, username, is_assigned_as_editor) VALUES (2,'Novi Sad','Serbia','miljancabrilo@yahoo.com','admin','$2a$10$hR0zMnB39JyIFfPvhyt.4u8ElXcDaQ1QSHKAxmN6e2ZvDCNATvbyK','admin','Bsc','admin', true);

INSERT INTO user_details (id, city, country, email, name, password, surname, title, username, is_assigned_as_editor) VALUES (3,'Novi Sad','Srbija','miljancabrilo@yahoo.com','editor1','$2a$10$xd3QqwDgaoXEO5gFhdqvTO3yy6edwN0.X2CIwmMY5bKJ4T7xdMgqm','editor1','Msc','editor1', false);
INSERT INTO user_details (id, city, country, email, name, password, surname, title, username, is_assigned_as_editor) VALUES (4,'Novi Sad','Serbia','miljancabrilo@yahoo.com','editor2','$2a$10$tAhkJ8fRIndplkj.v3o4wuGxZBo2vytIn78ucHxCuVXxYurY0V1pW','editor2','Phd','editor2', false);
INSERT INTO user_details (id, city, country, email, name, password, surname, title, username, is_assigned_as_editor) VALUES (5,'Novi Sad','Srbija','miljancabrilo@yahoo.com','editor3','$2a$10$/b28T.bKL6mktOKD0pGlB.qcYk0lWBCyBdGRuemLw0n6F2UkTMMtu','editor3','Bsc','editor3', false);

INSERT INTO user_details (id, city, country, email, name, password, surname, title, username, is_assigned_as_editor) VALUES (6,'Novi Sad','Novi Sad','miljancabrilo@yahoo.com','reviewer1','$2a$10$h68HZ42PMT0Q6IEzlZ4CeuARtnqisnNmBPVC5Ydbq41VYy.uAWoV2','reviewer1','Srbija','reviewer1',true);
INSERT INTO user_details (id, city, country, email, name, password, surname, title, username, is_assigned_as_editor) VALUES (7,'Novi Sad','Srbija','miljancabrilo@yahoo.com','reviwer2','$2a$10$jn57JPPBXuyOxMIGwPBAJeoOFyUnBeewhbTC0sEc2HGjlIrDvncnC','reviwer2','Bsc','reviwer2',true);
INSERT INTO user_details (id, city, country, email, name, password, surname, title, username, is_assigned_as_editor) VALUES (8,'Novi Sad','Srbija','miljancabrilo@yahoo.com','reviewer3','$2a$10$Ho1pzAOOp/YiQnBNlnrZu.TOhf5C16hMmSNUF.HxGgsegmD/QWLAe','reviewer3','Bsc','reviewer3',true);



INSERT INTO scientific_area (id, name) VALUES ('one', 'Biology');
INSERT INTO scientific_area (id, name) VALUES ('two', 'Mathematics');
INSERT INTO scientific_area (id, name) VALUES ('three', 'Geography');
INSERT INTO scientific_area (id, name) VALUES ('four', 'Physics');


INSERT INTO magazine VALUES (1,123,0x01,'magazineOne',0x01,0xACED0005737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C770800000010000000027372001B636F6D2E6E632E6D6F64656C2E536369656E746966696341726561E0C523EEF7A28C750200024C000269647400124C6A6176612F6C616E672F537472696E673B4C00046E616D6571007E000378707400036F6E6574000742696F6C6F677973720018636F6D2E6E632E6D6F64656C2E5573657244657461696C738FD55E30366375F102000B4A000269645A0012697341737369676E65644173456469746F724C00046369747971007E00034C0007636F756E74727971007E00034C0005656D61696C71007E00034C00046E616D6571007E00034C000870617373776F726471007E00034C000F736369656E746966696341726561737400104C6A6176612F7574696C2F4C6973743B4C00077375726E616D6571007E00034C00057469746C6571007E00034C0008757365726E616D6571007E000378700000000000000003007400084E6F766920536164740006537262696A617400176D696C6A616E63616272696C6F407961686F6F2E636F6D740007656469746F723174003C243261243130247864335171774467616F58454F35674668647176544F337979366564774E302E58324349776D4D5935624B4A34543778644D67716D7372002F6F72672E68696265726E6174652E636F6C6C656374696F6E2E696E7465726E616C2E50657273697374656E744261676563FD3F2082000C0200014C000362616771007E00087872003E6F72672E68696265726E6174652E636F6C6C656374696F6E2E696E7465726E616C2E416273747261637450657273697374656E74436F6C6C656374696F6EE9D3DC4DA9113BA502000A5A001B616C6C6F774C6F61644F7574736964655472616E73616374696F6E49000A63616368656453697A655A000564697274795A000B696E697469616C697A65645A000D697354656D7053657373696F6E4C00036B65797400164C6A6176612F696F2F53657269616C697A61626C653B4C00056F776E65727400124C6A6176612F6C616E672F4F626A6563743B4C0004726F6C6571007E00034C001273657373696F6E466163746F72795575696471007E00034C000E73746F726564536E617073686F7471007E0011787000FFFFFFFF0000007372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000371007E0009740028636F6D2E6E632E6D6F64656C2E5573657244657461696C732E736369656E74696669634172656173707070740007656469746F72317400034D7363740007656469746F72317371007E000274000374776F74000B4D617468656D617469637371007E000978,1);

INSERT INTO magazine_reviewers VALUES (1,6),(1,7);

INSERT INTO magazine_scientific_areas VALUES (1,'one'),(1,'two');

INSERT INTO user_details_scientific_areas (user_details_id, scientific_areas_id) VALUES (6, 'one')
INSERT INTO user_details_scientific_areas (user_details_id, scientific_areas_id) VALUES (7, 'one')
INSERT INTO user_details_scientific_areas (user_details_id, scientific_areas_id) VALUES (7, 'four')



