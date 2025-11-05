public class IProdutoRepository implements ProdutoRepository{
    
    private final DAOProduto DAOproduto = new DAOProduto();

    @Override
    public void salvar(Produto produto) {
        DAOProduto.inserir(Produto); 
    }

    @Override
    public List<Produto> buscarTodos() {
        return DAOProduto.buscarTodos(); 
    }

    @Override
    public Produto buscarPorId(int idProduto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }

    @Override
    public void atualizar(Produto produto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void deletar(int idProduto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }
}


}