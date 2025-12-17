package com.j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * 写一个类来遍历目录下的所有 .java 文件（或其他类型），查找包含关键词的行
 */
public class FileSearcher {
    private static final Logger logger = Logger.getLogger(FileSearcher.class.getName());
    private final List<SearchResult> searchResults;

    public FileSearcher() {
        this.searchResults = new ArrayList<>();
        logger.info("文件搜索初始化成功");
    }

    public List<SearchResult> getSearchResult() {
        return searchResults;
    }

    public void SearchFile(CommandLineParser parser) {
        String directory = parser.getDirectory();
        String keyword = parser.getKeyword();
        logger.info(String.format("开始搜索：目录-%s, 关键词-%s", directory, keyword));

        Path path = Path.of(directory);

        try (Stream<Path> stream = Files.walk(path)) {
            stream.filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".java"))
                    .forEach(p -> {
                        logger.info("正在扫描文件📃");
                        int[] count = {0};

                        logger.info("正在读取文件内容");
                        try (Stream<String> lines = Files.lines(p)) {
                            lines.filter(s -> s.contains(keyword))
                                    .forEach(s -> {
                                        SearchResult searchResult = new SearchResult();
                                        searchResult.setFilePath(p.toString());
                                        count[0]++;
                                        searchResult.setContent(s);
                                        searchResult.setLine(count[0]);
                                        searchResults.add(searchResult);
                                    });
                        } catch (IOException e) {
                            logger.severe("文件读取失败" + e.getMessage());
                        }
                    });


        } catch (IOException e) {
            System.out.println("文件读取失败" + e.getMessage());
        }

    }
}
