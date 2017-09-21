/*
 * Copyright (C) 2005-2017 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */

package net.truelicense.it.jax.rs

import net.truelicense.api.{License, VendorLicenseManager}
import net.truelicense.spi.io.MemoryStore

/**
 * A simple bean which holds a license key and its encoded license bean.
 * Note that the key and the bean get shared with the caller.
 */
class LicenseBeanAndKeyHolder(vm: VendorLicenseManager, _bean: License) {
  private val store = new MemoryStore
  val bean: License = (vm generator _bean save store).license()
  val key: Array[Byte] = store.data
}