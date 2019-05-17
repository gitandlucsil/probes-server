# probes-server

Um "server-side" da aplicação que registra os dados de leitura de sensores. Responsável para armazenar as leituras nos bancos de dados, recebida via requisição na API Rest, e a mesma aplicação responsável também para registrar se uma determinada leitura gerou algum alarme. A API também apresenta um metodo para gerar relátorios personalizados no formato PDF como saída para o usuario.

Para inserir dados no banco ou recupera-los, o servidor disponibiliza uma API Rest que trabalha com arquivos no formato JSON.

* Bibliotecas e frameworks utilizados:

- Spring Boot;
- Driver PostgreSQL;
- JasperReports;
