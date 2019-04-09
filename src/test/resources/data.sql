INSERT INTO COORDINATE (X, Y) VALUES (0, 1);
INSERT INTO COORDINATE (X, Y) VALUES (1, 1);
INSERT INTO MONSTER_POSITION (X, Y, FACING) VALUES (0, 0, 'UP');
INSERT INTO MONSTER_STATUS (ID, MONSTER_POSITION_X, MONSTER_POSITION_Y, MONSTER_POSITION_FACING) VALUES (100, 0, 0, 'UP');
INSERT INTO SURVIVOR (ID, NAME, IS_ALIVE, ACTIVATIONS_LEFT, MOVES_LEFT, MOVEMENT, SURVIVAL, INSANITY, ARMOUR_HEAD,
            ARMOUR_ARMS, ARMOUR_TORSO, ARMOUR_WAIST, ARMOUR_LEGS, POSITION_X, POSITION_Y)
            VALUES (101, 'Adam', true, 1, 1, 5, 0, 0, 0, 0, 0, 0, 0, 0, 1);
INSERT INTO SURVIVOR (ID, NAME, IS_ALIVE, ACTIVATIONS_LEFT, MOVES_LEFT, MOVEMENT, SURVIVAL, INSANITY, ARMOUR_HEAD,
            ARMOUR_ARMS, ARMOUR_TORSO, ARMOUR_WAIST, ARMOUR_LEGS, POSITION_X, POSITION_Y)
            VALUES (102, 'Eve', true, 1, 1, 5, 0, 0, 0, 0, 0, 0, 0, 1, 1);
INSERT INTO SHOWDOWN (ID, DESCRIPTION, GAME_STATUS, MONSTER_STATUS_ID) VALUES (103, 'game 1', 'RUNNING', 100);