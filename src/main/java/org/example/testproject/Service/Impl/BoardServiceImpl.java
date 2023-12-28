package org.example.testproject.Service.Impl;

import org.example.testproject.Service.BoardService;
import org.example.testproject.dto.BoardDTO;
import org.example.testproject.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<BoardDTO> getSyabeuList() {
        List<BoardDTO> getSyabeuList = boardMapper.selectSyabeuList();
        return getSyabeuList;
    }

    @Override
    public void setBoard(BoardDTO dto) {
        boardMapper.insertBoard(dto);
    }

    @Override
    public void updateBoard(BoardDTO dto) {
        boardMapper.updateBoard(dto);
    }

    @Override
    public void deleteBoard(BoardDTO dto) {
        boardMapper.deleteBoard(dto);
    }

    @Override
    public List<BoardDTO> getAdminList() {
        List<BoardDTO> adminList = boardMapper.selectAdminList();
        return adminList;
    }

    @Override
    public BoardDTO getId(Long boardNum) {
        BoardDTO board = boardMapper.selectBoardId(boardNum);
        return board;
    }
}
