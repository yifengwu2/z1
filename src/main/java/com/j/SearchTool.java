package com.j;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SearchTool {
    public static void main(String[] args) {
        try {
            CommandLineParser parser = new CommandLineParser(args);

            FileSearcher fileSearcher = new FileSearcher();

            fileSearcher.SearchFile(parser);

            List<SearchResult> results = fileSearcher.getSearchResult();

            System.out.println("✅ 搜索完成，共找到 " + results.size() + " 处匹配：");
            for (int i = 0; i < results.size(); i++) {
                SearchResult searchResult = results.get(i);
                System.out.printf("%d.文件：%s,行号 %d,内容%s%n",
                        i + 1, searchResult.getFilePath(),
                        searchResult.getLine(),
                        searchResult.getContent());
            }
        }catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
