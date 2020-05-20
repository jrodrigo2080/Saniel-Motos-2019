package Model;

public class ModelVenda {

    private int id;
    private float ValorVenda;
    private String dataVenda;
    private String nome;
    private String telefone;
    private String cpf;
    private String pagamento;
    private float valorCompra;
    private String placa;

    private String cod;
    private String marca;
    private String modelo;
    private String celindrada;
    private int ano;
    private String cor;
    private String dataCompra;
    private String observacao;
    private float valorVenda;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCelindrada() {
        return celindrada;
    }

    public void setCelindrada(String celindrada) {
        this.celindrada = celindrada;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    
    public float getValorVenda() {
        return ValorVenda;
    }

    public void setValorVenda(float ValorVenda) {
        this.ValorVenda = ValorVenda;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getPlaca();
    }

    /**
     * @return the pagamento
     */
    public String getPagamento() {
        return pagamento;
    }

    /**
     * @param pagamento the pagamento to set
     */
    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    /**
     * @return the valor
     */
    public float getValorCompra() {
        return valorCompra;
    }

    /**
     * @param valor the valor to set
     */
    public void setValorCompra(float valor) {
        this.valorCompra = valor;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

}
