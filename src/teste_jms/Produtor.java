package teste_jms;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.joda.time.Instant;

import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.QueueSender;
import javax.jms.DeliveryMode;
import javax.jms.QueueSession;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;

// Classe que produz mensagens e as envia para uma fila.
public class Produtor {

    // M�todo principal.
    public static void main(String[] args) throws Exception {
        
        // Propriedades para configura��o do ambiente de execu��o.
        Properties ambiente = new Properties();
        ambiente.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        ambiente.put(Context.PROVIDER_URL, "tcp://localhost:61616");
        ambiente.put("queue.filaDeMensagens", "FilaDeMensagens");
        
        // Contexto inicial para o ambiente de execu��o.
        InitialContext contexto = new InitialContext(ambiente);

        // Procura pela fila de mensagens.
        Queue fila = (Queue)contexto.lookup("filaDeMensagens");
        
        // Procura pela "f�brica" de conex�es �s filas de mensagem.
        QueueConnectionFactory fabricaDeConexoes = (QueueConnectionFactory)contexto.lookup("QueueConnectionFactory");
        
        // Cria uma conex�o � fila.
        QueueConnection conexao = fabricaDeConexoes.createQueueConnection();

        // Cria uma sess�o de acesso � fila.
        QueueSession sessao = conexao.createQueueSession(false, Session.DUPS_OK_ACKNOWLEDGE);

        // Cria objeto que permite enviar mensagens � fila.
        QueueSender produtor = sessao.createSender(fila);
        produtor.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

		int num1,num2;

		//usu�rio digita o n�mero a
		System.out.println("\n"+"Digite o n�mero 1");
		num1 = entrada.nextInt();

		//usu�rio digita o n�mero b
		System.out.println("Digite o n�mero 2");
		num2 = entrada.nextInt();
		
		//usu�rio digita o operador:
		System.out.println("Digite Operador(+,-,*,/:)")
		String operador = entrada.
		
		
        // Cria mensagem com a data/hora atual.
        TextMessage mensagem = sessao.createTextMessage(Instant.now().toString());

        // Envia a mensagem.
        produtor.send(mensagem);

        System.out.println("Mensagem enviada: " + mensagem.getText());

        // Fecha a conex�o.
        conexao.close();
    }
}
