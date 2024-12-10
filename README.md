# Order API

## Endpoints

### Obter Todos os Pedidos

**Descrição**: Busca todos os pedidos

**URL**: `/v1/orders`

**Método**: `GET`

**Parâmetros de Consulta**:
- `page` (opcional): Número da página a ser recuperada (padrão: `0`).
- `size` (opcional): Quantidade de itens por página (padrão: `10`).

   ```bash
   curl -X GET "http://localhost:8080/v1/orders?page=0&size=10" -H "Content-Type: application/json"
    ```
---

## Como Executar

1. **Rodar os serviços de infraestrutura**:
   Execute o Docker Compose para subir o PostgreSQL e o Kafka:
   ```bash
   docker-compose up
    ```

2. **Rodar os serviços de infraestrutura**:

   ```bash
   ./mvnw spring-boot:run
    ```



