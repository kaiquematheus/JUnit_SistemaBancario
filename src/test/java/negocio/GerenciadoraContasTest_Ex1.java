package negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Classe de teste criada para garantir o funcionamento das principais operações
 * sobre contas, realizadas pela classe {@link GerenciadoraContas}.
 * 
 * @author Kaíque Matheus
 * @date 29/01/2021 
 */
public class GerenciadoraContasTest_Ex1 {

    /**
	 * Teste básico da transferência de um valor da conta de um cliente para outro,
	 * estando ambos os clientes ativos e havendo saldo suficiente para tal transferência
	 * ocorrer com sucesso.
	 * 
	 * @author Kaíque Matheus
     * @date 29/01/2021 
	 */
    @Test
    public void testTransfereValor(){

    /* ############ Cenário ############ */

    // Criando algumas contas
    ContaCorrente conta01 = new ContaCorrente(1, 200, true);
    ContaCorrente conta02 = new ContaCorrente(2, 0, true);

    // Criando uma lista de contas e adicionando contas a ela
    List<ContaCorrente> contasBanco = new ArrayList<>();
    contasBanco.add(conta01);
    contasBanco.add(conta02);

    GerenciadoraContas gerContas = new GerenciadoraContas(contasBanco);

    /* ############ Execução ############ */
    
    // Vamos chamar o método transfereValor e tentar transferir 100 reais da conta 1 para a conta 2
    gerContas.transfereValor(1, 100, 2);

    /* ############ Verificação ############ */

    // Vamos verificar se o valores das contas estão corretas.
    assertEquals(100.0, conta01.getSaldo());
    assertEquals(100.0, conta02.getSaldo());
    }
}
