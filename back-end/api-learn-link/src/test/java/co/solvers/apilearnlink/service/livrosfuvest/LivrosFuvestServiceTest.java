package co.solvers.apilearnlink.service.livrosfuvest;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LivrosFuvestServiceTest {

    private final LivrosFuvestService livrosFuvestService = new LivrosFuvestService();

//    @Test
//    void lerArquivoTxt_readsValidFile() {
//        List<LivrosFuvestCriacaoDto> livros = livrosFuvestService.lerArquivoTxt();
//        assertFalse(livros.isEmpty());
//        assertEquals(1, livros.get(0).getId());
//        assertEquals(2023, livros.get(0).getAnoVestibular());
//        assertEquals("Some Book Title", livros.get(0).getTitulo());
//        assertEquals("Some Author", livros.get(0).getAutor());
//    }
//
//    @Test
//    void lerArquivoTxt_handlesMissingFile() {
//        // Simulate missing file scenario
//        List<LivrosFuvestCriacaoDto> livros = livrosFuvestService.lerArquivoTxt();
//        assertTrue(livros.isEmpty());
//    }
//
//    @Test
//    void lerArquivoTxt_handlesInvalidHeader() {
//        // Simulate invalid header scenario
//        List<LivrosFuvestCriacaoDto> livros = livrosFuvestService.lerArquivoTxt();
//        assertTrue(livros.isEmpty());
//    }
//
//    @Test
//    void lerArquivoTxt_handlesInvalidBookRecord() {
//        // Simulate invalid book record scenario
//        List<LivrosFuvestCriacaoDto> livros = livrosFuvestService.lerArquivoTxt();
//        assertTrue(livros.isEmpty());
//    }
//
//    @Test
//    void lerArquivoTxt_handlesInvalidTrailer() {
//        // Simulate invalid trailer scenario
//        List<LivrosFuvestCriacaoDto> livros = livrosFuvestService.lerArquivoTxt();
//        assertTrue(livros.isEmpty());
//    }
}