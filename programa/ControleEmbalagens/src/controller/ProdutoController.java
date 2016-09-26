/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Produto;
import dao.ProdutoDao;
import java.util.ArrayList;

/**
 *
 * @author j0nas
 */
public class ProdutoController {

    ProdutoDao produtoDao;

    public ProdutoController() {
        if (produtoDao == null) {
            produtoDao = new ProdutoDao();
        }
    }

    public boolean insereCadastroProduto(Produto produto) {
        if (produto.getIdProduto() != 0) {
            return produtoDao.updateCadastroProduto(produto);
        } else {
            return produtoDao.insereCadastroProduto(produto);
        }
    }

    public ArrayList<Produto> buscaCadastroProduto() {
        return produtoDao.buscaCadastroProduto();
    }

    public boolean excluirCadastroProduto(int id) {
        return produtoDao.excluirCadastroProduto(id);
    }

    public String buscarEmbalagem(int idEmbalagem) {
        return produtoDao.buscarEmbalagem(idEmbalagem);
    }
}
