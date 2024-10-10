package chipyard

import chisel3._

import org.chipsalliance.cde.config.{Config}

// ---------------------
// Xiangshan Configs
// ---------------------


class XiangshanConfig extends Config(
  new xiangshan.WithNXiangshanCores(1) ++
  new chipyard.config.AbstractConfig)