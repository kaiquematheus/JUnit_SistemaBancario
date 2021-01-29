package negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe de teste criada para garantir o funcionamento das principais operações
 * sobre clientes, realizadas pela classe {@link GerenciadoraClientes}.
 * 
 * @author Kaíque Matheus
 * @date 29/01/2021
 */
public class GerenciadoraClientesTest_Ex2 {
    
    private GerenciadoraClientes gerClientes;
    private int idCliente01 = 1;
    private int idCliente02 = 2;

    //O Before vai ser executado antes de cada teste
    @BeforeEach
    public void setUp(){
         
        /* ========== Cenário ========== */

        //Criamos alguns clientes
        Cliente cliente01 = new Cliente(idCliente01, "Kaíque", 23, "kaique@gmail.com", 123, true);
        Cliente cliente02 = new Cliente(idCliente02, "Matheus", 23, "matheus@gmail.com", 321, true);

        // Inserimos os clientes em uma lista de clientes do banco
        List<Cliente> clientesDoBanco = new ArrayList<>();
        clientesDoBanco.add(cliente01);
        clientesDoBanco.add(cliente02);

        // Instanciando a classe gerenciadora clientes
        gerClientes = new GerenciadoraClientes(clientesDoBanco);

    //    System.out.println("Before foi executado");
    }
    
    // O After vai ser executado apos cada teste
    @AfterEach
    public void tearDown(){
        gerClientes.limpa();

    //	System.out.println("After foi executado");
    }

    /**
	 * Teste básico da pesquisa de um cliente a partir do seu ID.
	 * 
	 * @author Kaíque Matheus
	 * @date 29/01/2021
	 */
    @Test
    public void testPesquisaCliente(){

        /* ========== Execução ========== */

        // Invocando o método pesquisa cliente passando o id de um dos clientes existentes
        Cliente cliente = gerClientes.pesquisaCliente(idCliente01);

        /* ========== Verificação ========== */

        // Verificamos se o pesquisa cliente retornou o cliente correto.
        assertEquals(idCliente01, cliente.getId());
        assertEquals("kaique@gmail.com", cliente.getEmail());
        assertEquals("Kaíque", cliente.getNome());
    }

     /**
	 * Teste básico da pesquisa de um cliente que não existe.
	 * 
	 * @author Kaíque Matheus
	 * @date 29/01/2021
	 */
    @Test
    public void testPesquisaClienteInexistente(){

        /* ========== Execução ========== */

        // Invocando o método pesquisa cliente passando o id de um dos clientes existentes
        Cliente cliente = gerClientes.pesquisaCliente(1001);

        /* ========== Verificação ========== */

        // Verificamos se o pesquisa cliente retornou o cliente correto.
        assertNull(cliente);
    }


    /**
	 * Teste básico da remoção de um cliente a partir do seu ID.
	 * 
	 * @author Kaíque Matheus
	 * @date 29/01/2021
	 */
    @Test
    public void testRemoveCliente(){

        /* ========== Execução ========== */
       
        // invocando o método que remove clientes passando o ide de um dos clientes existentes
        boolean clienteRemovido = gerClientes.removeCliente(idCliente02);

        /* ========== Verificação ========== */
        // Verificamos se o cliente foi removido corretamente.
        assertEquals(true, clienteRemovido);
        assertEquals(1, gerClientes.getClientesDoBanco().size());
        assertNull(gerClientes.pesquisaCliente(2));
    }

     /**
	 * Teste da tentativa de remoção de um cliente inexistente.
	 * 
	 * @author Kaíque Matheus
	 * @date 29/01/2021
	 */
    @Test
    public void testRemoveClienteInexistente(){

        /* ========== Execução ========== */
       
        // invocando o método que remove clientes passando o ide de um dos clientes existentes
        boolean clienteRemovido = gerClientes.removeCliente(1001);

        /* ========== Verificação ========== */
        // Verificamos se o cliente foi removido corretamente.
        assertEquals(false, clienteRemovido);
        assertEquals(2, gerClientes.getClientesDoBanco().size());
    }

    /**
	 * Validação da idade de um cliente quando a mesma está no intervalo permitido.
	 * 
	 * @author Kaíque
	 * @throws IdadeNaoPermitidaException 
	 * @date 29/01/2021
	 */
	@Test
	public void testClienteIdadeAceitavel_01() throws IdadeNaoPermitidaException {

		/* ========== Cenário ========== */		
		Cliente cliente = new Cliente(1, "Kaíque", 25, "kaique@gmail.com", 1, true);
		
		/* ========== Execução ========== */
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());
		
		/* ========== Verificações ========== */
		assertTrue(idadeValida);	
    }
    

     /**
	 * Validação da idade de um cliente quando a mesma está no intervalo permitido.
	 * 
	 * @author Kaíque
	 * @throws IdadeNaoPermitidaException 
	 * @date 29/01/2021
	 */
	@Test
	public void testClienteIdadeAceitavel_02() throws IdadeNaoPermitidaException {

		/* ========== Cenário ========== */		
		Cliente cliente = new Cliente(1, "Kaíque", 18, "kaique@gmail.com", 1, true);
		
		/* ========== Execução ========== */
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());
		
		/* ========== Verificações ========== */
		assertTrue(idadeValida);	
    }
    
    /**
	 * Validação da idade de um cliente quando a mesma está abaixo intervalo permitido.
	 * 
	 * @author Kaíque
	 * @throws IdadeNaoPermitidaException 
	 * @date 29/01/2021
	 */
	@Test
	public void testClienteIdadeAceitavel_03() throws IdadeNaoPermitidaException {

		/* ========== Cenário ========== */		
		Cliente cliente = new Cliente(1, "Kaíque", 17, "kaique@gmail.com", 1, true);

		/* ========== Execução ========== */
		try {
			gerClientes.validaIdade(cliente.getIdade());
			fail();
		} catch (Exception e) {
			/* ========== Verificações ========== */
            assertEquals(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA, e.getMessage());
		}	
    }
    
    /**
	 * Validação da idade de um cliente quando a mesma está acima intervalo permitido.
	 * 
	 * @author Kaíque
	 * @throws IdadeNaoPermitidaException 
	 * @date 29/01/2021
	 */
	@Test
	public void testClienteIdadeAceitavel_04() throws IdadeNaoPermitidaException {

		/* ========== Cenário ========== */		
		Cliente cliente = new Cliente(1, "Kaíque", 66, "kaique@gmail.com", 1, true);

		/* ========== Execução ========== */
		try {
			gerClientes.validaIdade(cliente.getIdade());
			fail();
		} catch (Exception e) {
			/* ========== Verificações ========== */
            assertEquals(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA, e.getMessage());
		}	
	}
}
