# Atividade Banco

## Objetivo:

Neste exercício, você irá testar funcionalidades de uma classe de gerenciamento de contas bancárias utilizando JUnit5. Seu foco será verificar operações de depósito, saque e consulta de saldo, garantindo que o comportamento da aplicação esteja correto em diferentes cenários.

Você irá trabalhar com a classe BankAccount, que representa uma conta bancária simples com as seguintes funcionalidades:

- String getAccountHolder(): Retorna o nome do titular da conta.
- double getBalance(): Retorna o saldo atual da conta.
- void deposit(double amount): Adiciona um valor positivo ao saldo da conta.
- boolean withdraw(double amount): Tenta retirar um valor da conta. Retorna true se o saque for bem-sucedido (saldo suficiente), e false se o saldo for insuficiente ou se o valor for inválido (negativo).

## Requisitos:

Você deve criar testes unitários para validar os seguintes comportamentos da classe BankAccount utilizando JUnit5 e apenas os métodos de asserção assertEquals, assertTrue, e assertFalse.

## Cenários para Teste:

### Saldo Inicial:

Verifique se, ao criar uma nova conta bancária, o saldo inicial é configurado corretamente.

**Exemplo**: Para uma conta criada com saldo inicial de 1000.00, o método getBalance() deve retornar 1000.00.

### Depósito:

Teste se o saldo da conta é atualizado corretamente após um depósito válido.

**Exemplo**: Deposite 500.00 em uma conta com saldo inicial de 1000.00 e verifique se o novo saldo é 1500.00.

**Caso inválido**: O método deposit não deve alterar o saldo caso o valor depositado seja negativo (exemplo: -100.00).

### Saque Bem-Sucedido:

Verifique se o saque é realizado com sucesso quando há saldo suficiente.

**Exemplo**: Sacar 400.00 de uma conta com saldo de 1000.00 deve ser permitido e o saldo final deve ser 600.00. O método withdraw deve retornar true.

**Saque Falho por Saldo Insuficiente**:

### Teste o caso onde o saldo da conta é insuficiente para um saque.

**Exemplo**: Tentar sacar 1500.00 de uma conta com saldo de 1000.00 deve falhar, e o saldo deve permanecer inalterado. O método withdraw deve retornar false.
Saque Inválido (Valor Negativo):

### Teste a falha no saque quando o valor a ser sacado é negativo.

**Exemplo**: Um saque de -200.00 deve falhar e o saldo da conta deve permanecer inalterado.