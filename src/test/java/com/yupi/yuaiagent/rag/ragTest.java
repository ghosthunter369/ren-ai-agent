package com.yupi.yuaiagent.rag;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ragTest {
    @Resource
    MyMarkdownReader myMarkdownReader;
    @Test
    public void test() {
        List<Document> documents = myMarkdownReader.loadMarkdown();
    }
}
