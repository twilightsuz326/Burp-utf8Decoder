# BurpSuite-utf8Decoder
Extention to perform UTF-8 and ShiftJIS URL encoding in BurpSuite.

![image](https://user-images.githubusercontent.com/64389838/205491097-60f83262-fbdb-4fcb-be2f-9e7d2d637e1b.png)

##  Getting Started
1. Download the jar in the repository. (build/jar/Burp-utf8Decoder.jar)
2. Upload it using the extender page

## Usage
* Right-click on Proxy History or Intercept and select Extension.
* Selecting a character will automatically decode and encode it

## Comment
* JFrame画面での`frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);`を設定するとBurpSuite 自体が終了してしまうため未定義。
  * メモリ解放が行われていない可能性あり。未検証。
