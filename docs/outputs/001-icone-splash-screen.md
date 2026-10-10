# Resultado 001 — Ícone e Splash Screen

## Objetivo
Personalizar a identidade visual do aplicativo Sorriso Marcado com um ícone próprio e uma tela de inicialização nativa.

## Implementação
- Ícone personalizado com símbolo de dente.
- Fundo da Splash Screen na cor verde-petróleo `#004C48`.
- Tema de inicialização separado do tema principal.
- Integração com a Activity principal.
- Dependência AndroidX Core SplashScreen configurada.

## Arquivos envolvidos
- `app/build.gradle.kts`
- `app/src/main/AndroidManifest.xml`
- `app/src/main/java/com/example/sorrisomarcado/MainActivity.kt`
- `app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml`
- `app/src/main/res/mipmap-anydpi-v26/ic_launcher_round.xml`
- `app/src/main/res/values/colors.xml`
- `app/src/main/res/values/themes.xml`

## Validação
O desenvolvedor confirmou que o ícone personalizado e a Splash Screen funcionaram corretamente. A compilação havia sido concluída com sucesso durante a implementação; não foram executados novos testes nesta etapa.

## Pendências
- Verificar o comportamento em diferentes versões do Android.
- Conferir o ícone em diferentes dispositivos e launchers.

## Status
Implementação concluída e funcionamento confirmado pelo desenvolvedor.
