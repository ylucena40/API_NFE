# API_NFE
 API referente ao site http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx
 
 --------------------------------
 Contendo 4 requisições REST:
 1.status atual dos serviços por estado
 -> /notas/statusAtual
 2.status atual do serviço filtrando por estado
 -> /notas/servicoEstado?servico=ServicoQualquer&estado=AutorizadorQualquer
3.status dos serviços por estado filtrando por data
-> /notas/statusData?data=yyyy-mm-dd
4.qual estado teve mais indisponibilidade de serviço
-> /notas/servicoIndisponivelEstado

---------------------------------
Legenda para os valores referentes a cada status dos serviços disponibilizados pelo site:
0 - Valor não referenciado na tabela.
1 - Status Verde: a consulta retornou resposta positiva.
(Ocorre após qualquer estágio.)
2 - Status Amarelo: a consulta retornou a primeira resposta negativa (falta Serviço ou falha de conexão).
(Ocorre após o Verde, permanecendo por até 10 minutos.
 Nesse estágio, uma resposta positiva à consulta retorna o estado para Verde.
As respostas negativas, ao final do tempo, evoluem o estado para Vermelho.)
3 - Status Vermelho: quando há respostas negativas seguidas para uma consulta (falta Serviço ou falha de conexão).
(Ocorre após o Amarelo.
Havendo uma resposta positiva a qualquer momento, o estado Verde é retomado.)
