package isso;


public class LimitadorAbas
    {
        MenuPrincipal principalLocal;
        MenuClientes clientesLocal;
        LimitadorAbas (MenuPrincipal principalLocal, MenuClientes clientesLocal, String nome)
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


