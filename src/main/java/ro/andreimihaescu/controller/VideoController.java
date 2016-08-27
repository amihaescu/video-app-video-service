package ro.andreimihaescu.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.andreimihaescu.dto.MovieResponse;
import ro.andreimihaescu.utils.MultipartFileSender;

import javax.servlet.http.HttpServletResponse;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class VideoController {

    private final static Logger LOGGER = Logger.getLogger(VideoController.class);

    @RequestMapping("/list")
    public List<MovieResponse> getMovieListByUserRole() {
        LOGGER.info("Returning movie list");
        return Arrays.asList(
                new MovieResponse(1L, "Silicon Valley", "S03E01", "","/api/movies/1"),
                new MovieResponse(2L, "Silicon Valley", "S03E02", "", "/api/movies/2"),
                new MovieResponse(3L, "Silicon Valley", "S03E03", "", "/api/movies/3")
        );
    }

    @RequestMapping("/{video}")
    public void getMovie(@PathVariable("video")Long videoNo, HttpServletRequest request, HttpServletResponse response) throws Exception{
        LOGGER.info(String.format("Serving video %d", videoNo));
        MultipartFileSender.fromPath(Paths.get("/media/andrei/Data2/Downloads/Silicon.Valley.S03E01.HDTV.x264-KILLERS/Silicon.Valley.S03E01.HDTV.x264-KILLERS.mkv"))
                .with(request)
                .with(response)
                .serveResource();
    }
}