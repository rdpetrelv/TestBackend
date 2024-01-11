INSERT INTO MACHINE_ENTITY(machine_Id, NAME, Available, Machine_Status, Progress) VALUES(1, 'MACHINE 01', 1, 1, 40);
INSERT INTO MACHINE_ENTITY(machine_Id, NAME, Available, Machine_Status, Progress) VALUES(2, 'MACHINE 02', 0, 1, 0);
INSERT INTO MACHINE_ENTITY(machine_Id, NAME, Available, Machine_Status, Progress) VALUES(3, 'MACHINE 03', 1, 1, 70);


INSERT INTO SENSOR_ENTITY(SENSOR_ID, sensor_Name, Measure, status, sensor_Type, MACHINE_MACHINE_ID) VALUES(1, 'Temperature Sensor', 12.6, 1, 'TEMPERATURE', 1);
INSERT INTO SENSOR_ENTITY(SENSOR_ID, sensor_Name, Measure, status, sensor_Type, MACHINE_MACHINE_ID) VALUES(2, 'Humidity Sensor', 70.5, 0, 'HUMIDITY', 1);
INSERT INTO SENSOR_ENTITY(SENSOR_ID, sensor_Name, Measure, status, sensor_Type, MACHINE_MACHINE_ID) VALUES(3, 'Light Sensor', 20.6, 0, 'LIGHT', 1);
INSERT INTO SENSOR_ENTITY(SENSOR_ID, sensor_Name, Measure, status, sensor_Type, MACHINE_MACHINE_ID) VALUES(4, 'Level Sensor', 80, 0, 'LEVEL', 1);


INSERT INTO RESERVATION(reservation_Id,reservation_available,reservation_user,reservation_date,reservation_Start,reservation_end,MACHINE_MACHINE_ID ) VALUES(1, '0', 'Ximena', '2024-01-10','08:00:00','12:00:00','1');
INSERT INTO RESERVATION(reservation_Id,reservation_available,reservation_user,reservation_date,reservation_Start,reservation_end,MACHINE_MACHINE_ID ) VALUES(2, '0', 'Fatima', '2024-01-12','20:00:00','22:00:00','2');
INSERT INTO RESERVATION(reservation_Id,reservation_available,reservation_user,reservation_date,reservation_Start,reservation_end,MACHINE_MACHINE_ID  ) VALUES(3, '0', 'David', '2024-01-13','14:00:00','16:00:00','3');