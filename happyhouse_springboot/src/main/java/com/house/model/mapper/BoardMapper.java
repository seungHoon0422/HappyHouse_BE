package com.house.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.house.model.BoardDto;
 
@Mapper
public interface BoardMapper {
	public List<BoardDto> selectBoard();
	public BoardDto selectBoardByNo(int articleno);
	public int insertBoard(BoardDto board);
	public int updateBoard(BoardDto board);
	public int deleteBoard(int articleno);
}