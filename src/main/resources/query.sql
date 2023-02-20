/* 더미데이터 */
insert into board (title, content, writer, regDate, password, cno)
    (select title, content, writer, regDate, password, 1 from board);


SELECT bno, c.category, title, writer, view, regDate as regDate, modDate as modDate
FROM board
    JOIN category c on c.cno = board.cno
ORDER BY bno DESC
    LIMIT 0, 5;

INSERT INTO board(cno, writer, password, title, content)
VALUES(?,?,?,?,?);

/* 특정게시글 조회 */
SELECT bno, category.category, title, writer, view, regDate, modDate
FROM board JOIN category ON board.cno = category.cno
WHERE bno = 124;