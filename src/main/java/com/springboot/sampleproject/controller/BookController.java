package com.springboot.sampleproject.controller;

import com.springboot.sampleproject.config.LargeDataRowHandler;
import com.springboot.sampleproject.model.dto.BookDto;
import com.springboot.sampleproject.service.impl.BookServiceImpl;
import com.springboot.sampleproject.util.LargeDataUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@Slf4j
public class BookController {

    private final BookServiceImpl bookService;

    @PostMapping("/booksave")
    public void saveBookList(
            @RequestParam Map<String,Object> param,
            HttpServletRequest request, HttpServletResponse response
    )  {
        bookService.saveBook(param);

        log.info("Insert Data end");

    }

    // book getAllDataList
    @GetMapping("/booklists")
    public List<BookDto> getAllDataList() {
        return bookService.getAllDataList();
    }

    // 12초
    @GetMapping("/selectBooks")
    public void getSelect(@RequestParam Map<String,Object> map, HttpServletRequest request, HttpServletResponse response){
        try {
//            Map map = new HashMap<>();
//            map.put("idx",10);

            response.setHeader("Accept","*/*");
            response.setContentType("application/json;charset=UTF-8");
//            response.setContentType("text/plain;charset=UTF-8");

            LargeDataRowHandler largeDataRowHandler =new LargeDataRowHandler(response.getWriter());
            bookService.select(map,largeDataRowHandler);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    // 20초..
    @GetMapping("/selectData")
    public void getSelectData(@RequestParam Map<String,Object> map, HttpServletRequest request, HttpServletResponse response){
        try {

//            response.setHeader("Accept","*/*");
//            response.setContentType("application/json;charset=UTF-8");
//            response.setContentType("text/plain;charset=UTF-8");

            LargeDataRowHandler largeDataRowHandler =new LargeDataRowHandler(response.getWriter());
            bookService.selectData(map,largeDataRowHandler);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    // 200만건 데이터 2.6초
    @GetMapping("/selectLargeData")
    public void getSelectLargeData(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String,Object> inParams){
        LargeDataUtil largeDataUtil = new LargeDataUtil(inParams,response);
//        response.setHeader("Accept","*/*");
//        response.setContentType("application/json;charset=UTF-8");

        bookService.selectLargeDataset(inParams,"ds_master");

        largeDataUtil.writeResponseToJson();
    }
}
