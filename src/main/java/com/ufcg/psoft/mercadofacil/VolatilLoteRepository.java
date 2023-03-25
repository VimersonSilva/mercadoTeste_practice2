package com.ufcg.psoft.mercadofacil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class VolatilLoteRepository implements LoteRepository<Lote, Long> {

   List<Lote> lotes = new ArrayList<>();

   @Override
   public Lote save(Lote lote) {
	   lotes.add(lote);
	   return lotes.get(lotes.size() - 1);
	   //return lotes.stream().findFirst().get();
   }

   @Override
   public Lote find(Long id) {	   
	   Lote loteEncontrado = null;
	   for(int i = 0; i < lotes.size(); i++) {
		   if(lotes.get(i).getId().equals(id)) {
			   loteEncontrado = lotes.get(i);
		   }
	   }
	   return loteEncontrado;
	   
	   //return lotes.get(Integer.parseInt("" + id));
   }

   @Override
   public List<Lote> findAll() {
	   return lotes;
   }
   @Override
   public Lote update(Lote lote) {
	   lotes.clear();
	   lotes.add(lote);
	   return lotes.stream().findFirst().orElse(null);
   }

   @Override
   public void delete(Lote lote) {
	   lotes.clear();
   }

   @Override
   public void deleteAll() {
	   lotes.clear();
   }

}
