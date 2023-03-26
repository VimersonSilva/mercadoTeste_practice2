package com.ufcg.psoft.mercadofacil.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ufcg.psoft.mercadofacil.model.Lote;

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
	   Lote loteUpdate = null;
	   for(int i = 0; i < lotes.size(); i++) {
		   if(lotes.get(i).getId().equals(lote.getId())) {
			   lotes.set(i, lote);
			   loteUpdate = lote;
		   }
	   }
	   return loteUpdate;

   }

   @Override
   public void delete(Lote lote) {
	   for(int i = 0; i < lotes.size(); i++) {
		   if(lotes.get(i).getId().equals(lote.getId())) {
			   lotes.remove(i);
		   }
	   }
   }

   @Override
   public void deleteAll() {
	   lotes.clear();
   }

}
