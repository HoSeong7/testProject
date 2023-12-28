package org.example.testproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.testproject.Service.BoardService;
import org.example.testproject.Service.MemberService;
import org.example.testproject.common.CommonCode;
import org.example.testproject.dto.BoardDTO;
import org.example.testproject.dto.CommonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/*")
public class MainController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private BoardService boardService;

    // 기본 index loading 화면
    @GetMapping("/index")
    public void index(Model model){
        // MenuList start
        CommonDTO dto = new CommonDTO();

        try {
            // 회원수
            dto.setMemberCount(memberService.getMemberTotalCount());
            // 이번주 예약
            // 1. 샤브샤브
            List<BoardDTO> syabeuList = boardService.getSyabeuList();
            log.error("잘 넘어오는지 확인해보자 !! : " + syabeuList);
            // 2.

            // 샤브샤브 메뉴



            // result 넘기기
            model.addAttribute("result", dto);
            model.addAttribute("syabeuList", syabeuList);

        }catch (Exception e){
            log.error("get Index error : " + e);
        }

    }

    // admin Page에서 현재 List
    @GetMapping("/adminList")
    public void adminList(Model model){
        try {
            List<BoardDTO> adminList = boardService.getAdminList();
            model.addAttribute("boardList", adminList);
        }catch (Exception e){
            log.error("get adminList error : " + e);
        }
    }

    @Value("${org.example.upload.path}")
    private String uploadPath;

    // admin List에서 게시물 추가
    @PostMapping("/boardRegister")
    public String boardRegister(@RequestParam("pictureurl") MultipartFile uploadfile, BoardDTO dto, RedirectAttributes redirectAttributes){

        CommonCode cCode = new CommonCode();

        try {
            // update
            if(dto.getRegDate() != null){
                if(uploadfile.getOriginalFilename() != "") {
                    dto.setUrl(cCode.filePath(uploadfile, uploadPath));
                }else {
                    BoardDTO idNum =  boardService.getId(dto.getBoardNum());
                    dto.setUrl(idNum.getUrl());
                }
                dto.setUpdateDate(LocalDateTime.now());
                boardService.updateBoard(dto);

            // insert
            }else{
                if(uploadfile.getOriginalFilename() != "") {
                    dto.setUrl(cCode.filePath(uploadfile, uploadPath));
                }else {

                }
                dto.setRegDate(LocalDateTime.now());
                dto.setUpdateDate(LocalDateTime.now());
                boardService.setBoard(dto);
            }
        }catch (Exception e){
            log.error("post boardRegister error : " + e);
        }
        return "redirect:/adminList";
    }

    @GetMapping("/boardMake")
    public String boardMake(@RequestParam(value = "boardNum", required = false) String boardNum, Model model){

        if (boardNum != null) {
            BoardDTO board = boardService.getId(Long.parseLong(boardNum));
            model.addAttribute("board", board);
            return "boardMake";
        }
        BoardDTO dto = new BoardDTO();
        model.addAttribute("board", dto);
        return "boardMake";
    }

    @GetMapping("display")
    public ResponseEntity<byte[]> getFile(String fileName, String size){
        ResponseEntity<byte[]> result = null;

        try {
            String srcFileName = URLDecoder.decode(fileName, "UTF-8");
            File file = new File(uploadPath + File.separator + srcFileName);
            HttpHeaders header = new HttpHeaders();

            header.add("Content-Type",  Files.probeContentType(file.toPath()));

            result = new ResponseEntity<>(
                    FileCopyUtils.copyToByteArray(file),
                    header,
                    HttpStatus.OK);


        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }

    @PostMapping("/deleteBoard")
    public ResponseEntity<String> deleteBoard(@RequestBody BoardDTO boardDTO){

        try {
            boardService.deleteBoard(boardDTO);
        }catch (Exception e) {
            log.error("deleteBoard error : " + e);
        }
        return ResponseEntity.ok("게시글이 성공적으로 삭제되었습니다.");
    }

}
