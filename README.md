# Framework Server Driven UI em Compose Multiplataforma para Aplicativos Android e iOS.

Neste experimento, pretendo testar o Compose Multiplataforma (CMP), uma tecnologia de desenvolvimento baseada em Kotlin (KMP) e Jetpack Compose (utilizado para desenvolver interfaces de usuário para aplicativos Android). Além disso, pretendo aplicar a abordagem Server Driven UI (SDUI) para a construção de telas. 

# Introdução

Iniciaremos esse projeto explicando de forma superficial o funcionamento da abordagem SDUI.

## Server Driven UI (SDUI)

O SDUI é uma abordagem de desenvolvimento na qual o servidor é responsável por estruturar a tela. É no servidor que definimos se a tela vai possuir um header (o texto a ser exibido no header inclusive), se terá uma lista de itens, se será um grid, se terá um footer com uma barra de navegação e quais ícones e textos devem aparecer nessa barra, etc. Aém disso, é no servidor que vamos definir o comportamento das telas, o sistema de navegação e quais ações acontecem ao interagir com a tela.

Já o aplicativo, conterá somente um catálogo de componentes visuais, ações e estilos mapeados de acordo com as respostas do servidor. Nenhuma tela precisa ser montada no aplicativo neste momento devido a abordagem do SDUI. 

Na prática, o aplicativo faz uma requisição ao servidor para construir uma tela. O servidor por sua vez, processa a requisição consultando todos os micro-serviços necessários, estrutura a tela e envia uma resposta no formato JSON (normalmente). O aplicativo interpreta essa resposta, transforma os dados do JSON em componentes visuais (que foram especificados em um catálogo de componentes) e renderiza a tela.

> É de suma importância que o contrato entre o servidor e o aplicativo seja bem definido, de forma que o servidor envie somente dados que o aplicativo consiga processar.

Abaixo segue exemplo de uma resposta do servidor para uma tela construída com SDUI. O JSON pode ser configurado da maneira que desejar, usei essa esta estrutura pois combina com as ferramentas que irei apresentar mais a diante.

```json
{
  "type": "ui-scaffold",
  "top": [
    {
      "type": "ui-top-bar",
      "content": [
        {
          "type": "ui-text",
          "text": "Title"
        }
      ]
    }
  ],
  "body": [
    {
      "type": "ui-lazy-column",
      "content": []
    }
  ],
  "bottom": []
}
```
Onde o`type` corresponde ao identificador do componente e as propriedades restantes representam os dados passados para o componente, podendo ser texto, número ou mesmo uma lista de outros componentes.

Com SDUI, a interface pode ser rapidamente atualizada e personalizada, pois as mudanças são feitas no servidor e refletidas instantaneamente no aplicativo sem a necessidade de atualização via lojas de aplicativos. Isso proporciona flexibilidade, permite uma personalização dinâmica, reduz o tamanho do aplicativo e separa a lógica de negócios da apresentação, garantindo consistência entre diferentes plataformas.


# Estratégia de Desenvolvimento e Organização do Projeto

Vamos dividir a implementação do Framework em 3 partes para simplificar o desenvolvimento e separar as responsabilidades.

1. Mecanismo
2. Catálogo
3. Solução

## Mecanismo

O Mecanismo é o Core do Framework, é aqui que vamos implementar a capacidade de interpretação dos comandos do servidor, ou seja, é aqui que vamos fazer com que o SDUI funcione de fato. 

Um Framework simples de SDUI precisa ser capaz de realizar duas tarefas principais:
1. Converter a resposta do servidor em objetos Kotlin.
2. Transformar esses objetos em componentes de tela.

O mecanismo pode conter outras funções como permissionamento, controle de versão, entre outras coisas que não vão ser abordadas agora.

### Domínio

É responsabilidade do mecanismo definir o domínio da aplicação. A ideia aqui é desenvolver o mecanismo de forma mais genérica possível e para isso vamos definir uma superclasse de domínio chamada `UIElement`. Dessa forma, todos os nós do JSON serão considerados UIElements.

```kotlin
/**
 * Classe base para todos os elementos do SDUI.
 */
@Serializable
abstract class UIElement
```

Os sub tipos do domínio UIElement são divididos em 3 grupos:
- Os **Componentes**, que representam todos os elementos renderizáveis, como botões, textos, imagens, etc. Em linhas gerais, são todos os componentes com escopo Composable, ou seja, anotados com `@Composable`. Vamos chamá-los de `UIComponent`s.
- As **Ações**, que representam os comportamentos que a tela pode possuir, como navegar ao tocar em um elemento, abrir uma tela, enviar dados do formulário, etc. Elas serão chamadas no contexto "não-composable" como no lambda onClick de um Button. Vamos chamá-los de `UIAction`s.
- Os **Estilos**, que representam a aparência dos elementos, como cor, tamanho, espaçamento, etc. Vamos seguir as definições dos `Modifier`s do Compose para configurar os estilos dos componentes. Vamos chamá-los de `UIModifier`s.

A definição dos três grupos de domínio é importante devido a implementação ser diferente para cada um deles. Ex.: A implementação de UIComponent deve possuir um método composable para renderizar os componentes, enquanto a implementação de UIAction precisa ser processada fora do contexto Composable, assim como a implementação de UIModifier precisa conter o escopo de Modifier do Compose.

## Catálogo

O catálogo conterá todos os elementos visuais, ações e estilos que serão mapeados pelo servidor. É aqui que vamos definir os sub tipos do domínio e fazer a implementação dos três grupos especificados no mecanismo. 

### Sub Tipos do domínio

Os Sub Tipos serão do tipo `data class` que servirão de contrato entre o servidor e o aplicativo, possuindo assim, todos os dados necessários para construir os elementos visuais.

Exemplo de sub tipos dos domínios:
- UIComponent -> UIButton, UIText, UIAppBar, UIScaffold, etc.
- UIAction -> UINavigate, UILog, UIAnalytics, UIManageBottomSheet, etc.
- UIModifier -> UIBackground, UIPadding, UISize, etc.

Os Sub Tipos do domínio deverão ser deserializados a partir do JSON retornado pelo servidor. 

### Implementação dos elementos visuais

Cada Sub Tipo possui uma implementação correspondente aos seus dados. É neste ponto que dizemos como o elemento deve se comportar.

Exemplo das implementações dos sub tipos dos domínios acima:
- UIComponentImpl -> UIButtonImpl, UITextImpl, UIAppBarImpl, UIScaffoldImpl, etc.
- UIActionImpl -> UINavigateImpl, UILogImpl, UIAnalyticsImpl, UIManageBottomSheetImpl, etc.
- UIModifierImpl -> UIBackgroundImpl, UIPaddingImpl, UISizeImpl, etc.

### Caso de Uso

Abaixo um exemplo de um componente que renderiza um texto na tela.

Retorno do servidor
```json
{
  "type": "ui-text",
  "text": "Texto a ser exibido"
}
```

Sub tipo do domínio
```kotlin
data class UIText(val text: String) : UIComponent()
```

Implementação do Sub Tipo
```kotlin
internal class UITextImpl : UIComponentImpl<UIText>() {

    @Composable
    override fun build(modifier: Modifier, component: UIText) {

        Text(
            modifier = modifier,
            text = component.text
        )
    }
}
```

## Solução

A solução é algo mais abrangente, é aqui que vamos deixar o Framework pronto para uso sem a necessidade de implementar a solução em várias telas. A ideia é construir algo totalmente genérico que possa ser reutilizado a medida que outras telas vão surgindo.

Temos um único e primordial requisito: 

> "O aplicativo precisa receber novas features sem a necessidade de atualização nas Lojas Oficiais.". 
 
Para isso, devemos:
- Construir uma tela que receba como parâmetro o nome da feature que deseja exibir.
- Usar esse parâmetro para buscar a especificação da tela no backend/servidor.
- Reutilizar essa mesma tela ao navegar por deeplink para outras features.


<BR><BR><BR><BR><BR><BR><BR><BR><BR> WIP <BR><BR><BR>

# Arquitetura

## Mecanismo

Requisitos:
- Deserializar a resposta do servidor em objetos Kotlin
- Consultar o catálogo de elementos (categorizados em Componentes, Ações, Estilos)
- Renderizar a tela de acordo com o catálogo

### Deserialização do JSON em objetos Kotlin

Para atender a esse primeiro requisito vamos utilizar o [Kolin Serialization](https://kotlinlang.org/docs/serialization.html) que é capaz de converter o JSON em um objeto Kotlin, que mapeará os componentes da tela.

Por que usar Kotlin Serialization?
* *Segurança de tipo*: Kotlin Serialization é integrado ao sistema de tipos do Kotlin, garantindo que os dados sejam desserializados com os tipos corretos.
* *Conciso*: A sintaxe para definir classes serializáveis é simples e direta.
* *Eficiente*: Kotlin Serialization é otimizado para desempenho e usa menos memória do que outras bibliotecas de serialização.
* *Multiplataforma*: Pode ser usado em diferentes plataformas, como Android, JVM, JavaScript e Native.




### Definição dos objetos Kotlin

A partir daqui, vamos começar a definir a camada de domínio do Framework SDUI. No primeiro momento, 



