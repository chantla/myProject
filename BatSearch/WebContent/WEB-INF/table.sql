-- 회원 저장 저장할 테이블
CREATE TABLE member1(
num NUMBER PRIMARY KEY,
email VARCHAR2(20),
name VARCHAR2(20),
pwd VARCHAR2(20),
regdate DATE
);
-- member 테이블의 primary key 값을 생성할 시퀀스
CREATE SEQUENCE member1_seq;
