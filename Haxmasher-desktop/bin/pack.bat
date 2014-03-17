cd "C:\Programmering\Eclipse workspace\libgdx-nightly-latest"
java -cp gdx.jar;gdx-tools.jar com.badlogic.gdx.tools.imagepacker.TexturePacker2 "%~dp0textures-original" "%~dp0textures" "textures"
cd %~dp0