package org.example.testproject.Service;

import org.example.testproject.dto.BoardDTO;
import org.example.testproject.dto.CommonDTO;
import org.example.testproject.entity.Board;

import java.util.List;
import java.util.Map;

public interface BoardService {

    List<BoardDTO> getSyabeuList();
    void setBoard(BoardDTO dto);
    void updateBoard(BoardDTO dto);
    void deleteBoard(BoardDTO dto);
    List<BoardDTO> getAdminList();
    List<BoardDTO> getAdditionList();
    List<BoardDTO> getDrinkList();
    List<BoardDTO> getEventList();

    BoardDTO getId(Long boardNum);
}
