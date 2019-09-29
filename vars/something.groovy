#!/usr/bin/env groovy

def call(Map params = [:]) {
    stage("Stage 1") {
      echo 'hi'
    }
    stage("Stage 2") {
      echo 'bye'
    }
}
