package com.ufcg.psoft.mercadofacil;

//import com.ufcg.psoft.mercadofacil.model.Lote;
//import com.ufcg.psoft.mercadofacil.model.Produto;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

<<<<<<< Updated upstream
=======
import com.ufcg.psoft.mercadofacil.model.Lote;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.LoteVolatilRepository;
>>>>>>> Stashed changes

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class VolatilLoteRepositoryTest {


   @Autowired
   LoteVolatilRepository driver;


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
       driver = new LoteVolatilRepository();
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
   void buscaPorLoteDentreLotes() {	   
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
   
   @Test
   @DisplayName("Procura Lote deletado")
   void buscaPorLoteDeletado() {	   
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
       driver.delete(loteExtra);
       
	   assertEquals(driver.findAll().size(),1);
	   assertNull(driver.find(loteExtra.getId()));	   
   }
   
   @Test
   @DisplayName("Procura Lote nao listado/cadastrado")
   void buscaPorLoteQueNaoEstaListado() {	   
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
              
	   assertEquals(driver.findAll().size(),1);
	   assertNull(driver.find(loteExtra.getId()));	   
   }

   @Test
   @DisplayName("Deleta Lote")
   void deletaLote() {   
	   driver.save(lote);
	   assertEquals(driver.findAll().size(),1);
	   driver.deleteAll();
	   assertEquals(driver.findAll().size(),0);
	   assertNull(driver.find(lote.getId()));	   
   }
   
   @Test
   @DisplayName("Procura Lote nao listado/cadastrado")
   void deletaLoteDentreLotes() {	   
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
       assertEquals(driver.findAll().size(),1);
       driver.save(loteExtra);       
	   assertEquals(driver.findAll().size(),2);
	   driver.delete(loteExtra);
	   assertEquals(driver.findAll().size(),1);
	   assertNull(driver.find(loteExtra.getId()));	
	   assertEquals(lote.getId().longValue(), 
			   driver.find(lote.getId()).getId().longValue());
   }
   
   @Test
   @DisplayName("Procura Lote nao listado/cadastrado")
   void deletaPrimeiroEUltimoLoteCadastrado() {	   
	   Produto produtoExtra = Produto.builder()
               .id(2L)
               .nome("Produto Extra")
               .codigoBarra("987654321")
               .fabricante("Fabricante Extra 2")
               .preco(125.36)
               .build();
       Lote loteExtra = Lote.builder()
               .id(2L)
               .numeroDeItens(100)
               .produto(produtoExtra)
               .build();
       
       Produto produtoExtraOther = Produto.builder()
               .id(3L)
               .nome("Produto Extra 2")
               .codigoBarra("987654321")
               .fabricante("Fabricante Extra 2")
               .preco(125.36)
               .build();
       Lote loteExtraOther = Lote.builder()
               .id(3L)
               .numeroDeItens(120)
               .produto(produtoExtraOther)
               .build();
       
       driver.save(lote);
       assertEquals(driver.findAll().size(),1);
       driver.save(loteExtra);       
	   assertEquals(driver.findAll().size(),2);
	   driver.save(loteExtraOther);       
	   assertEquals(driver.findAll().size(),3);
	   driver.delete(lote);
	   assertEquals(driver.findAll().size(),2);
	   driver.delete(loteExtraOther);
	   assertEquals(driver.findAll().size(),1);
	   assertNull(driver.find(lote.getId()));
	   assertNull(driver.find(loteExtraOther.getId()));
	   assertEquals(loteExtra.getId().longValue(), 
			   driver.find(loteExtra.getId()).getId().longValue());
   }
   
   @Test
   @DisplayName("Deleta os lotes ate lista ficar vazia")
   void deletaAteListaVazia() {	   
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
       assertEquals(driver.findAll().size(),1);
       driver.save(loteExtra);       
	   assertEquals(driver.findAll().size(),2);
	   driver.delete(loteExtra);
	   assertEquals(driver.findAll().size(),1);
	   driver.delete(lote);
	   assertEquals(driver.findAll().size(),0);
	   assertNull(driver.find(loteExtra.getId()));	
	   assertNull(driver.find(lote.getId()));	
   }
   
   @Test
   @DisplayName("atualiza um lote vazio")
   void updateLotesVazio() {	   
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
       
	   assertNull(driver.update(loteExtra));	
	   
   }
   
   @Test
   @DisplayName("Tenta atualizar lote com id diferente em uma lista com lotes presentes")
   void updateLotesIdDifferent() {	   
	   Produto produtoExtra = Produto.builder()
               .id(1L)
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
       assertEquals(driver.findAll().size(),1);
       driver.update(loteExtra);
	   assertNull(driver.update(loteExtra));	
	   
   }
   
   @Test
   @DisplayName("atualiza um lote com id produto diferente")
   void updateLote() {	   
	   Produto produtoExtra = Produto.builder()
               .id(2L)
               .nome("Produto Base")
               .codigoBarra("123456789")
               .fabricante("Fabricante Base")
               .preco(125.36)
               .build();
       Lote loteExtra = Lote.builder()
               .id(1L)
               .numeroDeItens(100)
               .produto(produtoExtra)
               .build();
       
       driver.save(lote);
       assertEquals(driver.findAll().size(),1);
       driver.update(loteExtra);       
       assertEquals(loteExtra.getId().longValue(), 
			   driver.find(loteExtra.getId()).getId().longValue());
       assertNotEquals(lote.getProduto().getId().longValue(), driver.find(loteExtra.getId()).getProduto().getId().longValue());	   
	   
   }
   
   @Test
   @DisplayName("atualiza um lote com nome do produto diferente")
   void updateLoteDifferentNameProduct() {	   
	   Produto produtoExtra = Produto.builder()
               .id(1L)
               .nome("Produto Base 1")
               .codigoBarra("123456789")
               .fabricante("Fabricante Base")
               .preco(125.36)
               .build();
       Lote loteExtra = Lote.builder()
               .id(1L)
               .numeroDeItens(100)
               .produto(produtoExtra)
               .build();
       
       driver.save(lote);
       assertEquals(driver.findAll().size(),1);
       driver.update(loteExtra);       
       assertEquals(loteExtra.getId().longValue(), 
			   driver.find(loteExtra.getId()).getId().longValue());
       assertNotEquals(lote.getProduto().getNome(), driver.find(loteExtra.getId()).getProduto().getId());	   
	   
   }
   
   @Test
   @DisplayName("atualiza um lote com codigo de barra do produto diferente")
   void updateLoteCodigoBarraProduto() {	   
	   Produto produtoExtra = Produto.builder()
               .id(1L)
               .nome("Produto Base")
               .codigoBarra("123456788")
               .fabricante("Fabricante Base")
               .preco(125.36)
               .build();
       Lote loteExtra = Lote.builder()
               .id(1L)
               .numeroDeItens(100)
               .produto(produtoExtra)
               .build();
       
       driver.save(lote);
       assertEquals(driver.findAll().size(),1);
       driver.update(loteExtra);       
       assertEquals(loteExtra.getId().longValue(), 
			   driver.find(loteExtra.getId()).getId().longValue());
       assertNotEquals(lote.getProduto().getCodigoBarra(), driver.find(loteExtra.getId()).getProduto().getCodigoBarra());	   
	   
   }
   
   @Test
   @DisplayName("atualiza um lote com fabricante do produto diferente")
   void updateLoteFabricanteProduto() {	   
	   Produto produtoExtra = Produto.builder()
               .id(1L)
               .nome("Produto Base")
               .codigoBarra("123456789")
               .fabricante("Fabricante Base 2")
               .preco(125.36)
               .build();
       Lote loteExtra = Lote.builder()
               .id(1L)
               .numeroDeItens(100)
               .produto(produtoExtra)
               .build();
       
       driver.save(lote);
       assertEquals(driver.findAll().size(),1);
       driver.update(loteExtra);       
       assertEquals(loteExtra.getId().longValue(), 
			   driver.find(loteExtra.getId()).getId().longValue());
       assertNotEquals(lote.getProduto().getFabricante(), driver.find(loteExtra.getId()).getProduto().getFabricante());	   
	   
   }
   
   @Test
   @DisplayName("atualiza um lote com preco do produto diferente")
   void updateLotePrecoProduto() {	   
	   Produto produtoExtra = Produto.builder()
               .id(1L)
               .nome("Produto Base")
               .codigoBarra("123456789")
               .fabricante("Fabricante Base")
               .preco(130)
               .build();
       Lote loteExtra = Lote.builder()
               .id(1L)
               .numeroDeItens(100)
               .produto(produtoExtra)
               .build();
       
       driver.save(lote);
       assertEquals(driver.findAll().size(),1);
       driver.update(loteExtra);       
       assertEquals(loteExtra.getId().longValue(), 
			   driver.find(loteExtra.getId()).getId().longValue());
       assertNotEquals(lote.getProduto().getPreco(), driver.find(loteExtra.getId()).getProduto().getPreco());	   
	   
   }
   
   @Test
   @DisplayName("atualiza um lote com números de itens do lote diferentes")
   void updateLoteNumerosItensLote() {	   
	   Produto produtoExtra = Produto.builder()
               .id(1L)
               .nome("Produto Base")
               .codigoBarra("123456789")
               .fabricante("Fabricante Base")
               .preco(125.36)
               .build();
       Lote loteExtra = Lote.builder()
               .id(1L)
               .numeroDeItens(150)
               .produto(produtoExtra)
               .build();
       
       driver.save(lote);
       assertEquals(driver.findAll().size(),1);
       driver.update(loteExtra);       
       assertEquals(loteExtra.getId().longValue(), 
			   driver.find(loteExtra.getId()).getId().longValue());
       assertNotEquals(lote.getNumeroDeItens(), driver.find(loteExtra.getId()).getNumeroDeItens());	   
	   
   }
   
   @Test
   @DisplayName("Testa funcao FindAll")
   void findAllTest() {
	   driver.save(lote);
	   assertEquals(driver.findAll().size(),1);
	   assertEquals(driver.findAll().get(0), lote);   
   }
   
   @Test
   @DisplayName("Testa funcao FindAll para dois lotes")
   void findAllDoisLotesTest() {
	   Produto produtoExtra = Produto.builder()
               .id(1L)
               .nome("Produto Base")
               .codigoBarra("123456789")
               .fabricante("Fabricante Base")
               .preco(125.36)
               .build();
       Lote loteExtra = Lote.builder()
               .id(2L)
               .numeroDeItens(150)
               .produto(produtoExtra)
               .build();
	   driver.save(lote);
	   driver.save(loteExtra);
	   assertEquals(driver.findAll().size(),2);
	   assertEquals(driver.findAll().get(0), lote);
	   assertEquals(driver.findAll().get(1), loteExtra);
   }
   
   @Test
   @DisplayName("Testa funcao FindAll para dois lotes cadastrados mas com"
   		+ " aplicacao do delete")
   void findAllDoisLotesComDeleteTest() {
	   Produto produtoExtra = Produto.builder()
               .id(1L)
               .nome("Produto Base")
               .codigoBarra("123456789")
               .fabricante("Fabricante Base")
               .preco(125.36)
               .build();
       Lote loteExtra = Lote.builder()
               .id(2L)
               .numeroDeItens(150)
               .produto(produtoExtra)
               .build();
       
	   driver.save(lote);
	   assertEquals(driver.findAll().size(), 1);
	   assertEquals(driver.findAll().get(0), lote);
	   driver.save(loteExtra);	   
	   assertEquals(driver.findAll().size(), 2);
	   assertEquals(driver.findAll().get(1), loteExtra);
	   driver.delete(loteExtra);
	   assertFalse(driver.findAll().contains(loteExtra));
   }
   
   @Test
   @DisplayName("Testa funcao FindAll para dois lotes")
   void DeleteAllDoisLotesTest() {
	   Produto produtoExtra = Produto.builder()
               .id(1L)
               .nome("Produto Base")
               .codigoBarra("123456789")
               .fabricante("Fabricante Base")
               .preco(125.36)
               .build();
       Lote loteExtra = Lote.builder()
               .id(2L)
               .numeroDeItens(150)
               .produto(produtoExtra)
               .build();
	   driver.save(lote);
	   driver.save(loteExtra);
	   assertEquals(driver.findAll().size(),2);
	   driver.deleteAll();
	   assertEquals(driver.findAll().size(),0);
   }
}
