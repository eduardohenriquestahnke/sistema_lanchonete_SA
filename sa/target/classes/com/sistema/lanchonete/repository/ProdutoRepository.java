public interface ProdutoRepository {

    void salvar(Produto produto); // Create
    List<Produto> buscarTodos(); // Read
    Produto buscarPorId(int idProduto); // Read by ID - Update
    void atualizar(Produto produto); // Update
    void deletar(int idProduto); // Delete
}

