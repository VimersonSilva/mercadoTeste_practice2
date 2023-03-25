package com.ufcg.psoft.mercadofacil;

//import com.ufcg.psoft.mercadofacil.model.Lote;
//import com.ufcg.psoft.mercadofacil.model.Produto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class VolatilLoteRepositoryTest {


   @Autowired
   VolatilLoteRepository driver;


   Lote lote;
   Lote resultado;
   Produto produto;


   @BeforeEach
   void setup() {
       produto = Produto.builder()
               .id(1L)
               .nome("Produto Base")
               .codigoBarra("123456789")
               .fabricante("Fabricante Base")
               .preco(125.36)
               .build();
       lote = Lote.builder()
               .id(1L)
               .numeroDeItens(100)
               .produto(produto)
               .build();
       driver = new VolatilLoteRepository();
   }


   @AfterEach
   void tearDown() {
       produto = null;
       driver.deleteAll();
   }


   @Test
   @DisplayName("Adicionar o primeiro Lote no repositorio de dados")
   void salvarPrimeiroLote() {
       resultado = driver.save(lote);


       assertEquals(driver.findAll().size(),1);
       assertEquals(resultado.getId().longValue(), lote.getId().longValue());
       assertEquals(resultado.getProduto(), produto);
   }
   
   @Test
   @DisplayName("Adicionar o segundo Lote (ou posterior) no repositorio de dados")
   void salvarSegundoLoteOuPosterior() {
       Produto produtoExtra = Produto.builder()
               .id(2L)
               .nome("Produto Extra")
               .codigoBarra("987654321")
               .fabricante("Fabricante Extra")
               .preco(125.36)
               .build();
       Lote loteExtra = Lote.builder()
               .id(2L)
               .numeroDeItens(100)
               .produto(produtoExtra)
               .build();
       driver.save(lote);


       resultado = driver.save(loteExtra);


       assertEquals(driver.findAll().size(),2);
       assertEquals(resultado.getId().longValue(), loteExtra.getId().longValue());
       assertEquals(resultado.getProduto(), produtoExtra);


   }
   
   @Test
   @DisplayName("Procura Lote")
   void buscaPorLote() {
	   driver.save(lote);
	   assertEquals(driver.findAll().size(),1);
	   assertEquals(lote.getId().longValue(), driver.find(lote.getId()).getId().longValue());	   
   }
   
   @Test
   @DisplayName("Procura Lote dentre vários Lotes")
   void buscaPorLote1() {	   
	   Produto produtoExtra = Produto.builder()
               .id(2L)
               .nome("Produto Extra")
               .codigoBarra("987654321")
               .fabricante("Fabricante Extra")
               .preco(125.36)
               .build();
       Lote loteExtra = Lote.builder()
               .id(2L)
               .numeroDeItens(100)
               .produto(produtoExtra)
               .build();
       
       driver.save(lote);
       driver.save(loteExtra);
       
	   assertEquals(driver.findAll().size(),2);
	   assertEquals(loteExtra.getId().longValue(), driver.find(loteExtra.getId()).getId().longValue());	   
   }
   
   @Test
   @DisplayName("Procura Lote Vazio")
   void buscaPorLoteVazio() {
	   driver.save(lote);
	   driver.deleteAll();
	   assertEquals(driver.findAll().size(),0);
	   assertNull(driver.find(lote.getId()));	   
   }


}
