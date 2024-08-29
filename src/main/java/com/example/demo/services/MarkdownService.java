package com.example.demo.services;


import lombok.RequiredArgsConstructor;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

@Service
public class MarkdownService {



    public String convertMarkdownToHtml(String markdown) {
        org.commonmark.node.Node document = getNode(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render((org.commonmark.node.Node) document);
    }

    private static org.commonmark.node.Node getNode(String markdown) {
        Parser parser = Parser.builder().build();
        org.commonmark.node.Node document = parser.parse(markdown);
        return document;
    }
}

