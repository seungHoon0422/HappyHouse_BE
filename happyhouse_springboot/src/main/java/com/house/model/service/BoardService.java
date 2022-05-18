package com.house.model.service;

import java.util.List;

import com.house.model.BoardDto;

public interface BoardService {
	public List<BoardDto> retrieveBoard();
	public BoardDto detailBoard(int articleno);
	public boolean writeBoard(BoardDto board);
	public boolean updateBoard(BoardDto board);
	public boolean deleteBoard(int articleno);
}
