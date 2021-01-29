package negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Classe de teste criada para garantir o funcionamento das principais operações
 * sobre clientes, realizadas pela classe {@link GerenciadoraClientes}.
 * 
 * @author Kaíque Matheus
 * @date 29/01/2021
 */
public class GerenciadoraClientesTest_Ex1 {

    /**
	 * Teste básico da pesquisa de um cliente a partir do seu ID.
	 * 
	 * @author Kaíque Matheus
	 * @date 29/01/2021
	 */
    @Test
    public void testPesquisaCliente(){

        /* ############ Cenário ############ */

        //Criamos alguns clientes
        Cliente cli1 = new Cliente(1, "Kaíque", 23, "kaique@gmail.com", 123, true);
        Cliente cli2 = new Cliente(2, "Matheus", 23, "matheus@gmail.com", 321, true);

        // Inserimos os clientes em uma lista de clientes do banco
        List<Cliente> clientesDoBanco = new ArrayList<>();
        clientesDoBanco.add(cli1);
        clientesDoBanco.add(cli2);

        // Instanciando a classe gerenciadora clientes
        GerenciadoraClientes gerClientes = new GerenciadoraClientes(clientesDoBanco);

        /* ############ Execução ############ */

        // Invocando o método pesquisa cliente passando o id de um dos clientes existentes
        Cliente cliente = gerClientes.pesquisaCliente(1);


        /* ############ Verificação ############ */

        // Verificamos se o pesquisa cliente retornou o cliente correto.
        assertEquals(1, cliente.getId());
        assertEquals("kaique@gmail.com", cliente.getEmail());
        assertEquals("Kaíque", cliente.getNome());
    }

    /**
	 * Teste básico da remoção de um cliente a partir do seu ID.
	 * 
	 * @author Kaíque Matheus
	 * @date 29/01/2021
	 */
    @Test
    public void testRemoveCliente(){

        /* ############ Cenário ############ */
         //Criamos alguns clientes
         Cliente cli1 = new Cliente(1, "Kaíque", 23, "kaique@gmail.com", 123, true);
         Cliente cli2 = new Cliente(2, "Matheus", 23, "matheus@gmail.com", 321, true);
 
         // Inserimos os clientes em uma lista de clientes do banco
         List<Cliente> clientesDoBanco = new ArrayList<>();
         clientesDoBanco.add(cli1);
         clientesDoBanco.add(cli2);
        
         // Instanciando a classe gerenciadora clientes
        GerenciadoraClientes gerClientes = new GerenciadoraClientes(clientesDoBanco);

        /* ############ Execução ############ */
        // invocando o método que remove clientes passando o ide de um dos clientes existentes
        boolean clienteRemovido = gerClientes.removeCliente(2);

        /* ############ Verificação ############ */
        // Verificamos se o cliente foi removido corretamente.
        assertEquals(true, clienteRemovido);
        assertEquals(1, gerClientes.getClientesDoBanco().size());
        assertNull(gerClientes.pesquisaCliente(2));
    }

}