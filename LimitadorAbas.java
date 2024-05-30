package paradigmasTrabalhoUm.Estrutural;


import paradigmasTrabalhoUm.GUI.InsereCliente;
import paradigmasTrabalhoUm.GUI.MenuClientes;
import paradigmasTrabalhoUm.GUI.MenuPrincipal;

public class LimitadorAbas
    {
        MenuPrincipal principalLocal;
        MenuClientes clientesLocal;
        public LimitadorAbas (MenuPrincipal principalLocal, MenuClientes clientesLocal, String nome)
        {
            this.principalLocal = principalLocal;
            this.clientesLocal = clientesLocal;
            InsereCliente insereCliente = new InsereCliente(this , nome);
        }

        public void setLimitador (int valor)
        {
            if (principalLocal != null)
            {
                principalLocal.updateInsereClienteAbrir(valor);
            }
            if (clientesLocal != null)
            {
                clientesLocal.updateInsereClienteAbrir(valor);
            }
        }
        public void atualizaLista ()
        {
            clientesLocal.recarregaLista();
        }
    }


