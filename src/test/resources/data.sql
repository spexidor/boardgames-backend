--INSERT INTO COORDINATE (X, Y) VALUES (0, 0);
--INSERT INTO COORDINATE (X, Y) VALUES (1, 0);
--INSERT INTO COORDINATE (X, Y) VALUES (0, 1);  --Adam
--INSERT INTO COORDINATE (X, Y) VALUES (1, 1);  --Eve
--INSERT INTO COORDINATE (X, Y) VALUES (11, 6); --Monster
--INSERT INTO COORDINATE (X, Y) VALUES (10, 6);

INSERT INTO SHOWDOWN (ID, DESCRIPTION, GAME_STATUS, TURN) VALUES (100, 'board 1', 'RUNNING', 1);

INSERT INTO MONSTER (SHOWDOWN_ID, POSITION_X, POSITION_Y, FACING, ACTIVATED_THIS_TURN, LEVEL, LAST_WOUNDED_BY)
VALUES (100, 11, 6, 'UP', false, 1, -1);

INSERT INTO SURVIVOR (ID, NAME, STATUS, BLEED, COURAGE, UNDERSTANDING, ACTIVATIONS_LEFT, MOVES_LEFT, MOVEMENT, SURVIVAL,
            POSITION_X, POSITION_Y, SHOWDOWN_ID, PRIORITY_TARGET, PERMANENT_PRIORITY_TARGET)
            VALUES (101, 'Adam', 'STANDING', 0, 0, 0, 1, 1, 5, 0, 0, 1, 100, false, false);
INSERT INTO SURVIVOR (ID, NAME, STATUS, BLEED, COURAGE, UNDERSTANDING, ACTIVATIONS_LEFT, MOVES_LEFT, MOVEMENT, SURVIVAL,
            POSITION_X, POSITION_Y, SHOWDOWN_ID, PRIORITY_TARGET, PERMANENT_PRIORITY_TARGET)
            VALUES (102, 'Eve', 'STANDING', 0, 0, 0, 1, 1, 5, 0, 1, 1, 100, false, false);

INSERT INTO SHOWDOWN_SURVIVORS (SHOWDOWN_ID, SURVIVORS_ID) VALUES (100, 101);
INSERT INTO SHOWDOWN_SURVIVORS (SHOWDOWN_ID, SURVIVORS_ID) VALUES (100, 102);

--INSERT INTO ATTACK (ID, BRAIN_DAMAGE, DAMAGE, IGNORE_EVASION, SPEED, TO_HIT_VALUE, AI_CARD_ID, TRIGGER_ID, TRIGGER_EFFECT_ID)
--VALUES (40, false, 1, false, 2, 2, 39, NULL, NULL);

--INSERT INTO CARD (DTYPE, ID, TITLE, HAS_FAILURE, HAS_REACTION, HAS_WOUND, TRAP, DECK_ID, ATTACK_ID, TARGET_RULE_ID)
--VALUES (AICard, 39, 'Basic Action', NULL, NULL, NULL, NULL, NULL, 40, 41);




