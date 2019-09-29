#!/usr/bin/env groovy

def call(Map params = [:]) {
  def level = params.containsKey('level') ? getLogLevelNum(params.level) : getLogLevelNum('DEBUG')
  def text = params.containsKey('text') ? params.text : ''
  def currentLevel = getLogLevelNum('INFO')
  if( level >= currentLevel){
    logMessage(level, text)
  }
  myerror()
}

def logMessage(level, text){
  switch(level) {
    case 0:
      echoColor(text: "[DEBUG] ${text}", colorfg: 'blue', colorbg: 'default')
    break
    case 1:
      echoColor(text: "[INFO] ${text}", colorfg: 'default', colorbg: 'default')
    break
    case 2:
      echoColor(text: "[WARN] ${text}", colorfg: 'yellow', colorbg: 'default')
    break
    case 3:
      echoColor(text: "[ERROR] ${text}", colorfg: 'red', colorbg: 'default')
    break
    default:
      echoColor(text: "[DEBUG] ${text}", colorfg: 'blue', colorbg: 'default')
    break
  }
}

def getLogLevelNum(level){
  def levelNum = 1
  switch(level) {
    case 'DEBUG':
      levelNum = 0
    break
    case 'INFO':
      levelNum = 1
    break
    case 'WARN':
      levelNum = 2
    break
    case 'ERROR':
      levelNum = 3
    break
    default:
      levelNum = 1
    break
  }
  return levelNum
}
