INSERT INTO MACHINE_ENTITY(machine_Id, NAME, Available, Machine_Status, Progress) VALUES(-2, 'TEST MACHINE', 0, 0, 40);

INSERT INTO SENSOR_ENTITY(SENSOR_ID, sensor_Name, Measure, status, sensor_Type, MACHINE_MACHINE_ID) VALUES(-1, 'TEST SENSOR', 12.6, 0, 'TEMPERATURE', -2);

INSERT INTO RESERVATION(reservation_Id,reservation_available,reservation_user,MACHINE_MACHINE_ID ) VALUES(-3, '0', 'TEST RESERVATION', -2);