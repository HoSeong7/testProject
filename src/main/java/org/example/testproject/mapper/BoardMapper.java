package org.example.testproject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.testproject.dto.BoardDTO;
import org.example.testproject.dto.CommonDTO;
import org.example.testproject.entity.Board;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    void insertBoard(BoardDTO dto);
    void updateBoard(BoardDTO dto);
    void deleteBoard(BoardDTO dto);
    List<BoardDTO> selectAdminList();
    List<BoardDTO> selectSyabeuList();
    BoardDTO selectBoardId(Long boardNum);
}
